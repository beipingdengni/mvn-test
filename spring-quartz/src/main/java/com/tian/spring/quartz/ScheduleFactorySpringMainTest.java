package com.tian.spring.quartz;

import com.tian.spring.quartz.annotion.CustomQuartz;
import com.tian.spring.quartz.annotion.QuartzCron;
import org.quartz.*;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

    public static void main(String[] args) throws SchedulerException, IOException {

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-schedule-factory.xml");
//        SchedulerFactoryBean schedulerFactoryBean = context.getBean("schedulerFactoryBean", SchedulerFactoryBean.class);
//        Scheduler scheduler = schedulerFactoryBean.getObject();
//
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        System.out.println("======> end <======");

        String groupName = "ScheduleFactorySpringMainTest---group";
        String trigerName = "ScheduleFactorySpringMainTest---trigger";
        String jobName = "ScheduleFactorySpringMainTest---job";

        JobKey jobKey = JobKey.jobKey(jobName, groupName);
        TriggerKey triggerKey = TriggerKey.triggerKey(trigerName, groupName);

        String cron = "0/10 * * * * ?";

        JobDetail jobDetail = JobBuilder.newJob(ScheduleFactorySpringMainTest.class).withIdentity(jobKey).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
                .build();

        boolean b = scheduler.checkExists(triggerKey);
        if (b) {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
        }
        boolean a = scheduler.checkExists(jobKey);
        if (a) {
            scheduler.deleteJob(jobKey);
        }

        scheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println("======> end <======");

    }


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("start ====> 启动开始执行job ===> " + System.currentTimeMillis());


        System.out.println("end ===> 启动开始执行job ===> " + System.currentTimeMillis());
    }


}
