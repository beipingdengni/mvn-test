## mvn-test

```xml

       <!--bean-->
       <bean id="mainTest" class="com.tian.spring.quartz.MainTest"/>
   
       <!--detail-->
       <bean id="mainTestDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
           <property name="targetObject" ref="mainTest"/>
           <property name="targetMethod" value="jobCronTest"/>
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

