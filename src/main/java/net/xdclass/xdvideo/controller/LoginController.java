package net.xdclass.xdvideo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.xdclass.xdvideo.model.Do.AdminsDo;
import net.xdclass.xdvideo.service.AdminsService;
import net.xdclass.xdvideo.utils.CommonUtils;
import net.xdclass.xdvideo.utils.JsonData;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private AdminsService adminsService;
    /**
     * shiro 没有登录的时候，跳转到这个方法
     * @return
     */
    @GetMapping("/notLogin")
    public JsonData notLogin() {
        return JsonData.buildFailedWithCodeAndMsg(401, "未登录");
    }

    /**
     * shiro 没有权限的时候，跳转到这个方法
     * @return
     */
    @GetMapping("/notRole")
    public JsonData notRole() {
        return JsonData.buildFailedWithCodeAndMsg(400, "无对应操作权限");
    }

    @GetMapping("/logout")
    public JsonData logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonData.buildFailedWithCodeAndMsg(200, "退出登录成功！");
    }

    @PostMapping("/login")
    public JsonData login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        AdminsDo adminsDo = adminsService.getOne(new QueryWrapper<AdminsDo>().eq("name", username));
        if (adminsDo == null) {
            return JsonData.buildFailedWithCodeAndMsg(400, "用户不存在");
        }
        // 验证密码

        if (!CommonUtils.checkPassword(password, adminsDo.getPassword())) {
            return JsonData.buildFailedWithCodeAndMsg(400, "密码错误");
        }
        String token = JwtUtils.createJwtToken(adminsDo);
        HashMap<String, Object> data = new HashMap<>();
        data.put("admins", adminsDo);
        data.put("token", token);
        return JsonData.buildSuccessWithMessageAndData("登录成功", data);
    }
}
