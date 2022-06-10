package net.xdclass.xdvideo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.model.dto.VideoDTO;
import net.xdclass.xdvideo.service.VideoService;
import net.xdclass.xdvideo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenlei
* @description 针对表【video】的数据库操作Service实现
* @createDate 2022-05-24 22:10:19
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService{

        @Autowired
        private VideoMapper videoMapper;

        @Override
        public Page<Video> getVideoList(Page<Video> page, VideoDTO dto) {
            return videoMapper.getVideoList(page, dto);
        }

        @Override
        public List<Video> getDeletedVideoList() {
            return videoMapper.getAllByDeletedAt();
        }
}




