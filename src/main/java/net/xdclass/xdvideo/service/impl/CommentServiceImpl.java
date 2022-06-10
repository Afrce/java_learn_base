package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.domain.Comment;
import net.xdclass.xdvideo.service.CommentService;
import net.xdclass.xdvideo.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author chenlei
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2022-05-24 22:10:19
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




