package com.tian.spring.kafka;

import com.tian.spring.kafka.config.BaseConfig;
import com.tian.spring.kafka.config.ProducerConfig;
import com.tian.spring.kafka.service.DaoService;
import com.tian.spring.kafka.service.ProducerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:43
 */
public class SpringProducer {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProducerConfig.class);

        ProducerService producerService = context.getBean(ProducerService.class);

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                producerService.printName("topic.one", "one====>" + i);
            } else {
                producerService.printName("topic.two", "two====>" + i);
            }
        }


    }
}
