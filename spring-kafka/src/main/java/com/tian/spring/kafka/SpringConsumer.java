package com.tian.spring.kafka;

import com.tian.spring.kafka.config.BaseConfig;
import com.tian.spring.kafka.config.ConsumerConfig;
import com.tian.spring.kafka.service.DaoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:43
 */
public class SpringConsumer {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfig.class);

    }
}
