package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.model.Do.PermissionsDo;
import net.xdclass.xdvideo.service.PermissionsService;
import net.xdclass.xdvideo.mapper.PermissionsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenlei
* @description 针对表【permissions】的数据库操作Service实现
* @createDate 2022-06-08 15:07:31
*/
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, PermissionsDo>
    implements PermissionsService{
    /**
     * @description 根据用户获取特殊权限
     * @param modelType
     * @param modelUuid
     * @return
     */
    @Override
    public List<PermissionsDo> findAllByModelTypeAndModelUuid(String modelType, String modelUuid) {
        return baseMapper.findAllByModelTypeAndModelUuid(modelType, modelUuid);
    }

    /**
     * 通过角色获取对应的权限
     * @param modelUuids
     * @return
     */
    @Override
    public List<PermissionsDo> findAllByRoleUuids(List<String> modelUuids) {
        return baseMapper.findAllByRoleUuids(modelUuids);
    }
}




