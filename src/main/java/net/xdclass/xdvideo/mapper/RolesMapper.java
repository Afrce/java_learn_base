package net.xdclass.xdvideo.mapper;
import java.util.List;

import net.xdclass.xdvideo.model.Do.RolesDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author chenlei
* @description 针对表【roles】的数据库操作Mapper
* @createDate 2022-06-08 15:07:31
* @Entity net.xdclass.xdvideo.model.Do.RolesDo
*/
public interface RolesMapper extends BaseMapper<RolesDo> {
    List<RolesDo> findAllByModelTypeAndModelUuid(@Param("modelType") String modelType,@Param("modelUuid") String modelUuid);
}




