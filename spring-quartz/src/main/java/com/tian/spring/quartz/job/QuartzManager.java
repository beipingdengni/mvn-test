package com.tian.spring.quartz.job;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: QuartzManager
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/14下午1:00
 */
public class QuartzManager {

    public static StdSchedulerFactory getStdSchedulerFactory() throws SchedulerException {
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "QuartzScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        properties.put("org.quartz.threadPool.threadCount", "5");
        properties.put("org.quartz.scheduler.threadPriority", "5");

        properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");

        StdSchedulerFactory fact = new StdSchedulerFactory(properties);
        return fact;
    }

    private static String JOB_GROUP_NAME = "job_group_name";

    private static String TRIGGER_GROUP_NAME = "trigger_group_name";

    public static void addJob(String jobName, Class cls, String time) throws SchedulerException {
        addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, cls, time);
    }

    public static void addJob(String jobName, String jobGroup, String triggerName, String triggerGoup, Class cls, String time) throws SchedulerException {

        Scheduler sched = getStdSchedulerFactory().getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(cls)
                .withDescription("this is a ram job")
                .withIdentity(jobName, jobGroup)
                .build();

        // 触发器名,触发器组
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time).withMisfireHandlingInstructionDoNothing();


        Trigger trigger = TriggerBuilder.newTrigger().startNow()
                .withDescription("")
                .withIdentity(triggerName, triggerGoup)
                .startAt(new Date())
                .withSchedule(cronScheduleBuilder)
                .build();

        sched.scheduleJob(jobDetail, trigger);
        if (!sched.isShutdown()) {

            sched.start();
        }

    }


    public static void modifyJobTime(String jobName, String time) throws SchedulerException {

        Scheduler scheduler = getStdSchedulerFactory().getScheduler();

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));

        if (trigger == null) {
            return;
        }

        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName,JOB_GROUP_NAME));
            Class objJobClass = jobDetail.getJobClass();
            removeJob(jobName);
            addJob(jobName, objJobClass, time);
        }

    }

    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) throws SchedulerException, ParseException {

        Scheduler scheduler = getStdSchedulerFactory().getScheduler();

        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));

        if (trigger == null) {
            return;
        }

        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
//            trigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(time)).startNow().build();
//            scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
            trigger.setCronExpression(time);
            scheduler.rescheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName), trigger);
        }

    }


    public static void removeJob(String jobName) throws SchedulerException {
        removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
    }

    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws SchedulerException {
        Scheduler scheduler = getStdSchedulerFactory().getScheduler();
        // 停止触发器
        scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
        // 移除触发器
        scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
    }


    public static void startJobs() throws SchedulerException {
        Scheduler scheduler = getStdSchedulerFactory().getScheduler();
        scheduler.start();

    }

    public static void shutdownJobs() throws SchedulerException {
        Scheduler sched = getStdSchedulerFactory().getScheduler();
        if (!sched.isShutdown()) {
            sched.shutdown();
        }
    }

}
