package com.tian.spring.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * @author tianbeiping
 * @Title: SpringScheduleFactoryMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30上午10:22
 */
public class ScheduleFactoryMemoryMainTest implements Job {

    private static Scheduler getStdSchedulerFactory() throws SchedulerException {
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "QuartzScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        properties.put("org.quartz.threadPool.threadCount", "5");
        properties.put("org.quartz.scheduler.threadPriority", "5");

        properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");

        StdSchedulerFactory fact = new StdSchedulerFactory(properties);
        Scheduler scheduler = fact.getScheduler();
        return scheduler;
    }


    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = getStdSchedulerFactory();


        JobDetail jobDetail = JobBuilder.newJob(ScheduleFactoryMemoryMainTest.class)
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

        scheduler.getContext();


    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Scheduler scheduler = jobExecutionContext.getScheduler();

        try {
            Set<JobKey> jobKeys1 = scheduler.getJobKeys(GroupMatcher.anyGroup());
            jobKeys1.forEach(m -> System.out.println(m.getGroup() + ":" + m.getName()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 启动开始执行job ===> " + System.currentTimeMillis());
    }

}
