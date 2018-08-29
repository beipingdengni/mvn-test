package com.tian.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: SpringMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/29下午6:35
 */
public class SpringMainTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-quartz.xml");

    }
}
