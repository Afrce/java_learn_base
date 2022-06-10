package net.xdclass.xdvideo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.xdclass.xdvideo.service.ProducerService;
import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private Queue queue;
    @Autowired
    private JmsMessagingTemplate jmsTemplate; //用来发送消息到broker的对象
    @Autowired
    private Topic topic;

    //发送消息，destination是发送到的队列，message是待发送的消息
    @Override
    public void sendMessage(Destination destination, String message) {

        jmsTemplate.convertAndSend(destination, message);

    }

    //发送消息，destination是发送到的队列，message是待发送的消息
    @Override
    public void sendMessage(final String message) {
        jmsTemplate.convertAndSend(queue, message);
    }


    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     */
    @Override
    public void publishMessage(Destination destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 发送消息，默认队列，message是待发送的消息
     * @param message
     */
    @Override
    public void publishMessage(final String message) {
        jmsTemplate.convertAndSend(topic, message);
    }

    /**
     * 发送延时消息
     * @param message
     * @param delayTime
     * @throws JsonProcessingException
     */
    @Override
    public void sendDelayedMessage(final String message, long delayTime) throws JsonProcessingException {
        Map<String, Object> headers = new HashMap<>();
        headers.put(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayTime * 1000);
        jmsTemplate.convertAndSend(queue, message, headers);
    }
}
