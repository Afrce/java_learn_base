package net.xdclass.xdvideo.jms;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    /**
     * rabbitmq消费者 点对点模式
     * @param message
     */
    @RabbitListener(queues = "way")
    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("消费者收到Rabbit消息：" + message);
    }

    /**
     * rabbitmq消费者 点对点模式
     * @param message
     */
    @RabbitListener(queues = "java_topic")
    @RabbitHandler
    public void receiveMessage2(String message) {
        System.out.println("消费者1收到Rabbit 订阅消息：" + message);
    }


    /**
     * rabbitmq消费者 点对点模式
     * @param message
     */
    @RabbitListener(queues = "java_topic")
    @RabbitHandler
    public void receiveMessage3(String message) {
        System.out.println("消费者2收到Rabbit 订阅消息：" + message);
    }

}
