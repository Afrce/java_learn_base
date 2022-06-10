package net.xdclass.xdvideo.service;

import net.xdclass.xdvideo.model.Do.RolesDo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author chenlei
* @description 针对表【roles】的数据库操作Service
* @createDate 2022-06-08 15:07:31
*/
public interface RolesService extends IService<RolesDo> {
    public List<RolesDo> findAllByModelTypeAndModelUuid(String modelType, String modelUuid);
}
