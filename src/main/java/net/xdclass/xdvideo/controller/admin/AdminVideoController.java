package net.xdclass.xdvideo.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.request.AddVideoRequest;
import net.xdclass.xdvideo.service.VideoService;
import net.xdclass.xdvideo.utils.JsonData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class AdminVideoController {
    @Autowired // 注入
    private VideoService videoService;

    @DeleteMapping("/video/{id}")
    public Object delete(@PathVariable Long id) {
        boolean delete =  videoService.removeById(id);
        if (!delete) {
            return JsonData.buildFailedWithCodeAndMsg(400, "删除失败");
        }
        return JsonData.buildSuccessWithMessage("删除成功");
    }

    @PutMapping("/video/{id}")
    public Object update(@PathVariable Long id, @RequestBody Video video) {
        video.setId(id);
        boolean update = videoService.updateById(video);
        if (!update) {
            return JsonData.buildFailedWithCodeAndMsg(400, "更新失败!");
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("video", videoService.getById(id));
        return JsonData.buildSuccessWithData(data);
    }

    @PostMapping("/video")
    public Object store(@RequestBody(required = false) @Valid AddVideoRequest request) {
        Video video = new Video();
        video.setCreateTime(new Date());
        BeanUtils.copyProperties(request, video);
        boolean save = videoService.save(video);
        if (!save) {
           return JsonData.buildFailedWithCodeAndMsg(400, "创建失败!");
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("video", video);
        return JsonData.buildSuccessWithData(data);
    }

}
