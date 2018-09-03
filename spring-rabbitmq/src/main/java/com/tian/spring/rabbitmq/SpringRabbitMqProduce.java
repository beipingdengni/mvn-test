package com.tian.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: SpringRabbitMqProduce
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/31下午6:23
 */
public class SpringRabbitMqProduce {

    public static void main(String[] args) throws InterruptedException {

        String queue_name = "direct-queue-1";

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-produce.xml");

//        AmqpTemplate rabbitTemplate = context.getBean("rabbitTemplate", AmqpTemplate.class);

        AmqpTemplate rabbitTemplate2 = context.getBean("rabbitTemplate2", AmqpTemplate.class);

        while (true) {
            for (int i = 0; i < 100; i++) {
                rabbitTemplate2.convertAndSend(queue_name, "spring --- hello world  ,  ===>  " + i);
            }
            System.out.println("end   ======>  end");
            TimeUnit.SECONDS.sleep(10);
        }


    }
}
