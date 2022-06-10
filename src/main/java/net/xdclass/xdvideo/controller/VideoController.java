package net.xdclass.xdvideo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.xdclass.xdvideo.annotation.ApiLog;
import net.xdclass.xdvideo.domain.User;
import net.xdclass.xdvideo.domain.Video;
import net.xdclass.xdvideo.exceiption.JwtException;
import net.xdclass.xdvideo.mapper.VideoMapper;
import net.xdclass.xdvideo.model.dto.VideoDTO;
import net.xdclass.xdvideo.service.ProducerService;
import net.xdclass.xdvideo.service.RabbitMQService;
import net.xdclass.xdvideo.utils.CommonUtils;
import net.xdclass.xdvideo.service.VideoService;
import net.xdclass.xdvideo.utils.JsonData;
import net.xdclass.xdvideo.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
@Api(tags = "视频模块", value = "视频控制器")
public class VideoController {
    @Autowired // 注入
    private VideoService videoService;
    @Autowired // 注入
    private HttpServletRequest request;

    @Autowired
    private ProducerService producerService; //用来发送消息到broker的对象

    @Autowired
    private RabbitMQService rabbitMQService;

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @ApiOperation("获取视频列表")
    @ApiLog(value = "获取视频列表")
    @GetMapping(value="/videos")
    public JsonData videos(
            @ApiParam(name = "page", value = "当前页面", example = "1")
            @RequestParam(value = "page", defaultValue = "1") int page,
            @ApiParam(name = "size", value = "每页多少条", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Subject subject = SecurityUtils.getSubject();

        System.out.println(subject.isPermitted("finance.tax.periods.action"));


        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("create_time");
        Page videoPage =  videoService.page(new Page<>(page, size), queryWrapper);
        Object data =  CommonUtils.formatPage(videoPage, "videos");
        return JsonData.buildSuccessWithMessageAndData("数据获取成功", data);
    }

    @PostMapping(value="/videos/test")
    public Object videosTest(@RequestBody VideoDTO videoDto) {
        Page videoPage =  videoService.getVideoList(new Page<Video>(videoDto.getPage(), videoDto.getSize()), videoDto);
        Object data =  CommonUtils.formatPage(videoPage, "videos");
        return JsonData.buildSuccessWithMessageAndData("数据获取成功", data);
    }


    @GetMapping("/video/{id}")
    public Object video(@PathVariable Long id) {
        Video video = videoService.getById(id);
        if (video == null) {
            return JsonData.buildFailedWithCodeAndMsg(400, "参数错误，未找到指定的video信息");
        }
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("video", video);
        return JsonData.buildSuccessWithData(data);
    }

//    @GetMapping("/token")
//    public Object testToken() {
//        User user = new User();
//        user.setId(123);
//        user.setName("TEST");
//        user.setHeadImg("1231313");
//        String jwt = JwtUtils.createJwtToken(user);
//        HashMap<String, Object> data = new HashMap<String, Object>();
//        data.put("token", jwt);
//        data.put("parse", JwtUtils.parseToken(jwt));
//        return JsonData.buildSuccessWithData(data);
//    }

    @GetMapping("/active")
    public Object testActiveMQ() {
        producerService.sendMessage("133");
        return 123;
    }

    @GetMapping("/topic")
    public Object testActiveMQTopic() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("topic", "topic");
        data.put("message", "topic message");
        data.put("message2", "topic message2");
        String message = JSON.toJSONString(data);
        producerService.publishMessage(message);
        return 123;
    }

    @GetMapping("/delay")
    public Object testActiveDealy() throws JsonProcessingException {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("topic", "topic");
        String message = JSON.toJSONString(data);
        producerService.sendDelayedMessage(message, 60); // 延迟10s
        return 123;
    }

    @GetMapping("/rabbit")
    public Object testRabbit() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("topic", "topic");
        String message = JSON.toJSONString(data);
        rabbitMQService.sendMessage(message);
        return 123;
    }
    @GetMapping("/rabbit/topic")
    public Object testRabbitTopic() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("topic", "topic");
        String message = JSON.toJSONString(data);
        rabbitMQService.sendTopicMessage(message);
        return 123;
    }

    @GetMapping("/rabbit/delay")
    public Object testRabbitDelay() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("topic", "topic");
        String message = JSON.toJSONString(data);
        rabbitMQService.sendDelayMessage(message, 10); // 延迟10s
        return 123;
    }

    @Transactional
    @GetMapping("/test/transaction")
    public Object testRabbitTopicDelay() {
        Video video = new Video();
        video.setId(1);
        video.setPoint(9.9);
        videoService.updateById(video);
        throw new JwtException("测试事务");
    }

    // 获取被逻辑删除的数据
    @GetMapping("/videos/delete")
    public Object videosDelete() {
        List<Video> data =  videoService.getDeletedVideoList();
        return JsonData.buildSuccessWithMessageAndData("数据获取成功", data);
    }
}
