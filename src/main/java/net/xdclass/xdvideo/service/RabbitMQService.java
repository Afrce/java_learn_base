package net.xdclass.xdvideo.service;

public interface RabbitMQService {

    public void sendMessage(final String message);

    public void sendTopicMessage(final String message);

    public void sendDelayMessage(final String message, int delayTime);
}
