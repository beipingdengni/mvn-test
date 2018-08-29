## mvn-test

```xml
    <!--配置 spring  线程池-->
    <bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="8"/> <!--核心线程数 -->
        <property name="maxPoolSize" value="16"/> <!--最大线程数 -->
        <property name="keepAliveSeconds" value="3000"/> <!--线程最大空闲时间 -->
        <property name="queueCapacity" value="200"/> <!-- 队列大小 -->
        <property name="threadNamePrefix" value="TASK_EXECUTOR"/>
        <property name="rejectedExecutionHandler">
            <bean class="java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy"/>
        </property>
    </bean>

```

