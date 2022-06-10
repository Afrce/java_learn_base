package net.xdclass.xdvideo;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@SpringBootApplication
@MapperScan("net.xdclass.xdvideo.mapper")
@EnableJms
public class XdvideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(XdvideoApplication.class, args);
    }
}
