package net.xdclass.xdvideo.component;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class ActiveMQComponent {
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("comment.queue");
    }

    @Bean
    public Topic topic(){
        System.out.println("注册topic");
        return new ActiveMQTopic("comment.topic");
    }

    // topic独立的容器
    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(activeMQConnectionFactory);
        return factory;
    }
}
