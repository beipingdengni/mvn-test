package com.tian.spring.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: SpringScheduleFactoryMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30上午10:22
 */
public class ScheduleFactoryMainTest implements Job {

    public static void main(String[] args) throws SchedulerException {


        Properties properties = new Properties();
        /**
         * org.quartz.scheduler.instanceName = MyScheduler
         * org.quartz.threadPool.threadCount = 3
         * org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
         */
        properties.put("org.quartz.scheduler.instanceName", "QuartzScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        properties.put("org.quartz.threadPool.threadCount", "5");
        properties.put("org.quartz.scheduler.threadPriority", "5");
        //properties.put("org.quartz.scheduler.class", "org.quartz.simpl.SimpleThreadPool");

        properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        /**
         * JDBC 配置使用
         *
         * org.quartz.dataSource.qzDS.driver:com.mysql.jdbc.Driver
         * org.quartz.dataSource.qzDS.URL:jdbc:mysql://localhost:3306/quartz_test
         * org.quartz.dataSource.qzDS.user:root
         * org.quartz.dataSource.qzDS.password:root
         * org.quartz.dataSource.qzDS.maxConnection:10
         *
         */


        StdSchedulerFactory fact = new StdSchedulerFactory(properties);
        Scheduler scheduler = fact.getScheduler();


        JobDetail jobDetail = JobBuilder.newJob(ScheduleFactoryMainTest.class)
                .withDescription("this is a ram job")
                .withIdentity("job1", "group1")
                .build();

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionDoNothing();

        Trigger trigger = TriggerBuilder.newTrigger().startNow()
                .withDescription("")
                .withIdentity("trigger1", "group1")
                .startAt(new Date())
                .withSchedule(cronScheduleBuilder)
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();


    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" 启动开始执行job ===> " + System.currentTimeMillis());
    }

}
