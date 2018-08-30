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
public class ScheduleFactoryJdbcMainTest implements Job {

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

        /**
         * 默认
         * properties.put("org.quartz.scheduler.class", "org.quartz.simpl.SimpleThreadPool");
         * 表示 内存本地运行
         * properties.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
         *
         */

        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.misfireThreshold", "60000");
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.dataSource", "myDS");
        properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        properties.put("org.quartz.jobStore.isClustered", "true");
        properties.put("org.quartz.jobStore.clusterCheckinInterval", "20000");

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

        properties.put("org.quartz.dataSource.myDS.driver", "com.mysql.jdbc.Driver");
        properties.put("org.quartz.dataSource.myDS.URL", "jdbc:mysql://localhost:3306/quartz_test?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        properties.put("org.quartz.dataSource.myDS.user", "root");
        properties.put("org.quartz.dataSource.myDS.password", "123456");
        properties.put("org.quartz.dataSource.myDS.maxConnections", "10");
        //properties.put("org.quartz.dataSource.myDS.validationQuery", "select 1 ");


        StdSchedulerFactory fact = new StdSchedulerFactory(properties);
        Scheduler scheduler = fact.getScheduler();


        JobDetail jobDetail = JobBuilder.newJob(ScheduleFactoryJdbcMainTest.class)
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
        if (scheduler.checkExists(trigger.getJobKey())) {
            scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, trigger);
        }

        scheduler.start();

    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(" 启动开始执行job ===> " + Math.ceil(System.currentTimeMillis() / 1000));
    }

}
