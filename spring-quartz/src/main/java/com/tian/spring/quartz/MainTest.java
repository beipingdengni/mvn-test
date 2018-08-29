package com.tian.spring.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/29下午12:11
 */
public class MainTest {


    public static void main(String[] args) throws InterruptedException {


//        MethodInvokingJobDetailFactoryBean methodInvok = new MethodInvokingJobDetailFactoryBean();
//
//        methodInvok.setTargetObject(new MainTest());
//        methodInvok.setTargetMethod("jobCronTest");
//        methodInvok.setBeanName("methodInvok_1");
//
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//
//        cronTriggerFactoryBean.setJobDetail(methodInvok.getObject());
//        cronTriggerFactoryBean.setBeanName("cronTriggerFactoryBean_1");
//        cronTriggerFactoryBean.setCronExpression("0/1 * * * * ?");
//
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//
//        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
//
//        while (true) {
//            Thread.sleep(10000);
//        }

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-quartz.xml");

    }


    public void jobCronTest() {
        System.out.println(" asdd ===== > " + System.currentTimeMillis());
    }
}
