
spring acvtivemq 练习 
----
```xml
     <!--配置链接池-->
    <bean id="pooledConnectionFactory" class="org.apache.activemq.pool.PooledConnectionFactory">
        <property name="connectionFactory">
            <bean class="org.apache.activemq.ActiveMQConnectionFactory">
                <constructor-arg name="userName" value="admin"/>
                <constructor-arg name="password" value="admin"/>
                <constructor-arg name="brokerURL" value="tcp://127.0.0.1:61616"/>
            </bean>
        </property>
        <property name="maxConnections" value="200"/>
    </bean>

    <!--使用缓存可以提升效率-->
    <bean id="connectionFactory" class="org.springframework.jms.connection.CachingConnectionFactory">
        <property name="targetConnectionFactory" ref="pooledConnectionFactory"/>
        <property name="sessionCacheSize" value="1"/>
    </bean>
    
    <bean id="defaultQueue" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg name="name" value="default"/>
    </bean>
    <!--配置 发送消息模板-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="defaultDestination" ref="defaultQueue"/>
        <property name="connectionFactory" ref="connectionFactory"/>
    </bean>
    <!--配置消费者-->
    <bean id="consumerMq" class="com.tian.spring.activemq.SpringConsumerMq"/>
    <bean class="org.springframework.jms.listener.DefaultMessageListenerContainer">
        <property name="connectionFactory" ref="pooledConnectionFactory"/>
        <property name="destinationName" value="default-spring"/>
        <property name="messageListener" ref="consumerMq"/>
    </bean>


```