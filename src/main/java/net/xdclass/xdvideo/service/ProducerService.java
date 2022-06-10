package net.xdclass.xdvideo.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.jms.Destination;

public interface ProducerService {
    /**
     * 功能描述：指定消息队列，还有消息
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message);

    /**
     * 功能描述：使用默认消息队列， 发送消息
     * @param message
     */
    public void sendMessage( final String message);

    /**
     * 指定消息队列，发送消息
     * @param destination
     * @param message
     */
    public void publishMessage(Destination destination, final String message);

    /**
     * 默认消息队列，发送消息
     * @param message
     */
    public void publishMessage(final String message);

    /**
     * 默认队列发送延时消息
     * @param message
     * @param delayTime
     */
    public void sendDelayedMessage(final String message, long delayTime) throws JsonProcessingException;
}
