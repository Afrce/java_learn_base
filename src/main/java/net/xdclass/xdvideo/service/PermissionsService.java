package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.model.Do.PermissionsDo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chenlei
* @description 针对表【permissions】的数据库操作Service
* @createDate 2022-06-08 15:07:31
*/
public interface PermissionsService extends IService<PermissionsDo> {
    public List<PermissionsDo> findAllByModelTypeAndModelUuid(String modelType, String modelUuid);

    public List<PermissionsDo> findAllByRoleUuids(List<String> modelUuids);
}
