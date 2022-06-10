package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.service.UserService;
import net.xdclass.xdvideo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author chenlei
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-05-24 22:10:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




