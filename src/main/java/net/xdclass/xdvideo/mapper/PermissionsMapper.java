package net.xdclass.xdvideo.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

import net.xdclass.xdvideo.model.Do.PermissionsDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author chenlei
* @description 针对表【permissions】的数据库操作Mapper
* @createDate 2022-06-08 15:07:31
* @Entity net.xdclass.xdvideo.model.Do.PermissionsDo
*/
public interface PermissionsMapper extends BaseMapper<PermissionsDo> {
    List<PermissionsDo> findAllByModelTypeAndModelUuid(@Param("modelType") String modelType,@Param("modelUuid") String modelUuid);

    List<PermissionsDo> findAllByRoleUuids(@Param("modelUuids") List<String> modelUuids);
}




