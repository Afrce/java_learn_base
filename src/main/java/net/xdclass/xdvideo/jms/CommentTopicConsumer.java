package net.xdclass.xdvideo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommentTopicConsumer {

    @JmsListener(destination = "comment.topic", containerFactory = "topicListenerContainer") // 指定消息监听的队列
    public void commentConsumerListenTopic(String message)
    {
        System.out.println("收到消息：" + message);
    }

    /**
     * 收到消息：{"id":1,"content":"你好，我是消费者"}, 指定容器工厂 否则只会消费topic导致P2P消息无法消费
     * @param message
     */
    @JmsListener(destination = "comment.topic", containerFactory = "topicListenerContainer") // 指定消息监听的队列
    public void commentConsumerListenTopic2(String message)
    {
        System.out.println("收到消息2：" + message);
    }
}
