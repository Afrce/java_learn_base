package net.xdclass.xdvideo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "java_topic_test";
    public static final String QUEUE = "java_topic_test";

    // 定义延时队列相关
    public static final String DELAY_QUEUE = "delay_queue";
    public static final String DELAY_EXCHANGE = "delay_exchange";
    public static final String DELAY_ROUTING_KEY = "delay_routing_key";

    @Bean
    public Exchange testExchange() {
        // 声明交换机 持久化
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue testQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    /**
     * 绑定交换机和队列
     * @return
     */
    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue())
                .to(testExchange())
                .with("")
                .noargs();
    }

    // 声明一个延时队列的交换机
    @Bean
    public Exchange delayExchange() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "fanout");
        return new ExchangeBuilder(DELAY_EXCHANGE, "x-delayed-message")
                .durable(true)
                .withArguments(args)
                .build();
    }

    // 声明一个延迟队列
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    // 声明一个延迟队列的绑定
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue())
                .to(delayExchange())
                .with(DELAY_ROUTING_KEY)
                .noargs();
    }
}
