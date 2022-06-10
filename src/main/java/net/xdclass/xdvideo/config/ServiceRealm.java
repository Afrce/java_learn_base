package net.xdclass.xdvideo.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.xdclass.xdvideo.interceoter.JWTToken;
import net.xdclass.xdvideo.model.Do.AdminsDo;
import net.xdclass.xdvideo.model.Do.RolesDo;
import net.xdclass.xdvideo.service.AdminsService;
import net.xdclass.xdvideo.service.PermissionsService;
import net.xdclass.xdvideo.service.RolesService;
import net.xdclass.xdvideo.utils.CommonUtils;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServiceRealm extends AuthorizingRealm {

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private RolesService rolesService;

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 授权
        String token = principals.getPrimaryPrincipal().toString();

        String name = JwtUtils.getUsername(token);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        AdminsDo adminsDo = adminsService.getOne(new QueryWrapper<AdminsDo>().eq("name", name));
        if (adminsDo == null) {
            throw new AuthenticationException("用户不存在");
        }
        // 获取roles
        List<RolesDo> roles = rolesService.findAllByModelTypeAndModelUuid("Modules\\Admin\\Entities\\Admin", adminsDo.getUuid());
        Set<String> userRoles = roles.stream().map(t->t.getName()).collect(Collectors.toSet());
        // role 的权限
        List<String> roleUuids = roles.stream().map(t->t.getUuid()).collect(Collectors.toList());
        // 角色权限
        Collection<String> rolePermissions = permissionsService.findAllByRoleUuids(roleUuids)
                .stream()
                .map(t -> t.getName())
                .collect(Collectors.toList());

        Collection<String> userPermissions = permissionsService.findAllByModelTypeAndModelUuid(
                "Modules\\Admin\\Entities\\Admin",
                adminsDo.getUuid()
        ).stream().map(t -> t.getName()).collect(Collectors.toList());

        // 设置权限和角色
        info.setRoles(userRoles);
        info.addStringPermissions(rolePermissions);
        info.addStringPermissions(userPermissions);
        // 获取permissions

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        String token = (String) auth.getPrincipal(); // token
        String username = JwtUtils.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token 不可用");
        }
        AdminsDo adminsDo = adminsService.getOne(new QueryWrapper<AdminsDo>().eq("name", username));
        if (adminsDo == null) {
            throw new AuthenticationException("用户不存在");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
}
