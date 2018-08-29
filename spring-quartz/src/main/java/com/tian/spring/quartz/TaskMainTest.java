package com.tian.spring.quartz;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/29下午12:11
 */
public class TaskMainTest {


    public static void main(String[] args) throws InterruptedException, SchedulerException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-task.xml");

    }

}
