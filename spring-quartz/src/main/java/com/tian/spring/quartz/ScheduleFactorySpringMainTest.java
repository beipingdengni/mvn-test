package com.tian.spring.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Date;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: SpringScheduleFactoryMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30上午10:22
 */
public class ScheduleFactorySpringMainTest implements Job {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-schedule-factory.xml");
        SchedulerFactoryBean schedulerFactoryBean = context.getBean("schedulerFactoryBean", SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getObject();


    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" 启动开始执行job ===> " + System.currentTimeMillis());
    }

}
