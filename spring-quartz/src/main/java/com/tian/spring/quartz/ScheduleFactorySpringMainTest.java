package com.tian.spring.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: SpringScheduleFactoryMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30上午10:22
 */
public class ScheduleFactorySpringMainTest {

    public static void main(String[] args) throws SchedulerException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-schedule-factory.xml");
        Scheduler scheduler = context.getBean("schedulerFactoryBean", StdScheduler.class);
        scheduler.start();

        JobDetail jobDetail = context.getBean("customDetail", JobDetail.class);
        CronTrigger trigger = context.getBean("customTrigger", CronTrigger.class);
        boolean a = scheduler.checkExists(trigger.getJobKey());
        if (a) {
            scheduler.pauseTrigger(trigger.getKey());
            scheduler.unscheduleJob(trigger.getKey());
        }
        boolean b = scheduler.checkExists(jobDetail.getKey());
        if (b) {
            scheduler.deleteJob(jobDetail.getKey());
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }


}
