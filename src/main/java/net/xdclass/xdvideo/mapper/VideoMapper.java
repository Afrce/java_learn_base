package net.xdclass.xdvideo.mapper;
import java.util.Date;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.xdclass.xdvideo.model.dto.VideoDTO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import net.xdclass.xdvideo.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author chenlei
* @description 针对表【video】的数据库操作Mapper
* @createDate 2022-05-24 22:10:19
* @Entity net.xdclass.xdvideo.domain.Video
*/
@Repository
public interface VideoMapper extends BaseMapper<Video> {
    Page<Video> getVideoList(Page<Video> page, @Param("dto") VideoDTO dto);

    List<Video> getAllByDeletedAt();
}




