package net.xdclass.xdvideo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.xdclass.xdvideo.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import net.xdclass.xdvideo.model.dto.VideoDTO;

import java.util.List;

/**
* @author chenlei
* @description 针对表【video】的数据库操作Service
* @createDate 2022-05-24 22:10:19
*/
public interface VideoService extends IService<Video> {
    public Page<Video> getVideoList(Page<Video> page, VideoDTO videoDto);

    public List<Video> getDeletedVideoList();
}
