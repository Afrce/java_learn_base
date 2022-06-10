package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.model.Do.RolesDo;
import net.xdclass.xdvideo.service.RolesService;
import net.xdclass.xdvideo.mapper.RolesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenlei
* @description 针对表【roles】的数据库操作Service实现
* @createDate 2022-06-08 15:07:31
*/
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, RolesDo>
    implements RolesService{
    public List<RolesDo> findAllByModelTypeAndModelUuid(String modelType, String modelUuid) {
        return baseMapper.findAllByModelTypeAndModelUuid(modelType, modelUuid);
    }
}




