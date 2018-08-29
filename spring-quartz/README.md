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

