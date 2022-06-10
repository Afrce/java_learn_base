package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.domain.VideoOrder;
import net.xdclass.xdvideo.service.VideoOrderService;
import net.xdclass.xdvideo.mapper.VideoOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author chenlei
* @description 针对表【video_order】的数据库操作Service实现
* @createDate 2022-05-24 22:10:19
*/
@Service
public class VideoOrderServiceImpl extends ServiceImpl<VideoOrderMapper, VideoOrder>
    implements VideoOrderService{

}




