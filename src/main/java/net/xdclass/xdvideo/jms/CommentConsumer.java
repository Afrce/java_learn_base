package net.xdclass.xdvideo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommentConsumer {

    @JmsListener(destination = "comment.queue") // 指定消息监听的队列
    public void commentConsumerListen(String message)
    {
        System.out.println("收到消息：" + message);
    }
}
