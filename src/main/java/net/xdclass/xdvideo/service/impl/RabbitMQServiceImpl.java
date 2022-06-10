package net.xdclass.xdvideo.service.impl;

import net.xdclass.xdvideo.config.RabbitMQConfig;
import net.xdclass.xdvideo.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(final String message) {
        rabbitTemplate.convertAndSend("way", message);
    }

    /**
     * 发送消息，fanout模式 不需要设置destination 第一个参数是exchange名称
     * @param message
     */
    @Override
    public void sendTopicMessage(final String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "",  message);
    }

    @Override
    public void sendDelayMessage(final String message, int delayTime) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("delayTime", delayTime);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.DELAY_EXCHANGE,
                RabbitMQConfig.DELAY_ROUTING_KEY,
                message,
                msg -> {
                     msg.getMessageProperties().setDelay(delayTime * 1000);
                     return msg;
                });
    }
}
