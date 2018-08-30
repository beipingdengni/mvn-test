## mvn-test

```xml
       <!--bean-->
       <bean id="mainTest" class="com.tian.spring.quartz.MainTest"/>
       <!--detail-->
       <bean id="mainTestDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
           <property name="targetObject" ref="mainTest"/>
           <property name="targetMethod" value="jobCronTest"/>
           <!--是否允许并发：默认 true -->
           <property name="concurrent" value="false"/>
       </bean>
       <!--cron-->
       <bean id="mainTestTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
           <property name="jobDetail" ref="mainTestDetail"/>
           <property name="cronExpression" value="0/1 * * * * ?"/>
           <property name="misfireInstruction" value="2"/>
       </bean>
       <!--scheduleFactory-->
       <bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
           <property name="triggers">
               <list>
                   <ref bean="mainTestTrigger"/>
               </list>
           </property>
       </bean>
```
#### 介绍 CronTriggerFactoryBean 配置参数 misfireInstruction
```text
基本判断条件
CronTrigger : misfireInstruction >= -1 && misfireInstruction <= 2

聪明策略
MISFIRE_INSTRUCTION_SMART_POLICY = 0 
启动先执行一次
MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1
启动后，不做任何
MISFIRE_INSTRUCTION_DO_NOTHING = 2
```

#### Quartz 链接数据库使用   查看 ScheduleFactoryJdbcMainTest 类

```java
         
        // 基本的文件配置
         
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
```


#### spring boot  处理quartz

```java
@Configuration
public class SchedulerConfig {
    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
       return new QuartzInitializerListener();
    }
    /*
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name="Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}

```

#### spring mvc 中Quartz
```xml
    <listener>
        <listener-class>
            org.quartz.ee.servlet.QuartzInitializerListener
        </listener-class>
    </listener>
```

#### web 中配置
```java

@Service("quartzService")
public class QuartzServiceImpl implements QuartzService{
	
	@Autowired
	private Scheduler quartzScheduler;
	
	@Override
	public void addJob(String jobName, String jobGroupName, String triggerName,String triggerGroupName, Class cls, String cron) {
		try {
			// 获取调度器
			Scheduler sched = quartzScheduler;
			// 创建一项作业
			JobDetail job = JobBuilder.newJob(cls)
					.withIdentity(jobName, jobGroupName).build();
			// 创建一个触发器
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron))
					.build();
			// 告诉调度器使用该触发器来安排作业
			sched.scheduleJob(job, trigger);
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改定时器任务信息
	 */
	@Override
	public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup,String triggerName, String triggerGroup, String cron) {
		try {
			Scheduler sched = quartzScheduler;
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
					.triggerKey(oldtriggerName, oldtriggerGroup));
			if (trigger == null) {
				return false;
			}

			JobKey jobKey = JobKey.jobKey(oldjobName, oldjobGroup);
			TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName,oldtriggerGroup);

			JobDetail job = sched.getJobDetail(jobKey);
			Class jobClass = job.getJobClass();
			// 停止触发器
			sched.pauseTrigger(triggerKey);
			// 移除触发器
			sched.unscheduleJob(triggerKey);
			// 删除任务
			sched.deleteJob(jobKey);
			
			addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass,
					cron);
			
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void modifyJobTime(String triggerName, String triggerGroupName,String time) {
		try {
			Scheduler sched = quartzScheduler;
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				ct.getTriggerBuilder()
						.withSchedule(CronScheduleBuilder.cronSchedule(time))
						.build();
				// 重启触发器
				sched.resumeTrigger(TriggerKey.triggerKey(triggerName,triggerGroupName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = quartzScheduler;
			// 停止触发器
			sched.pauseTrigger(TriggerKey.triggerKey(triggerName,triggerGroupName));
			// 移除触发器
			sched.unscheduleJob(TriggerKey.triggerKey(triggerName,triggerGroupName));
			// 删除任务
			sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void startSchedule() {
		try {
			// 启动 触发器
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void shutdownSchedule() {
		try {
			// 停掉触发器
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void pauseJob(String jobName, String jobGroupName) {
		try {
		    // 停止触发器
			quartzScheduler.pauseJob( JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void resumeJob(String jobName, String jobGroupName) {
		try {
		    // 重新开始
			quartzScheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

```

