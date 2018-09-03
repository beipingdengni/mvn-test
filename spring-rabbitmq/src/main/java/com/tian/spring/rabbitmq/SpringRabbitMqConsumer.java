package com.tian.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: SpringRabbitMqProduce
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/31下午6:23
 */
public class SpringRabbitMqConsumer implements MessageListener {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-consumer.xml");

    }

    public void onMessage(Message message) {
        try {
            String body = new String(message.getBody(), "UTF-8");
            System.out.println(body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
