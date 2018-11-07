

##### activemq 消息选择器Selector
```text
生产者

Destination send_destination = session.createQueue("order_queue");  
MessageProducer producer = session.createProducer(send_destination);  
for(int i =0;i<300;i++){  
   // 创建一个文本消息  
   TextMessage message =  session.createTextMessage("A-张三-"+i);  
   // 这里我们分别设置对应的消息信息，当成是一组消息  
   message.setStringProperty("JMSXGroupID","A");  
   producer.send(message);  

   TextMessage message1 =  session.createTextMessage("B-李四-"+i);  
   message1.setStringProperty("JMSXGroupID","B");  
   producer.send(message1);  
}  

消费者A

Destination destination = session.createQueue("order_queue");  
// 创建消费者  
MessageConsumer consumer = session.createConsumer(destination,"JMSXGroupID='A'");  
consumer.setMessageListener(new MessageListener() {  
 @Override  
 public void onMessage(Message message) {  
     TextMessage textMessage = (TextMessage) message;  
     try {  
         System.out.println("A："+textMessage.getText());  
     } catch (JMSException e) {  
         e.printStackTrace();  
     }  
 }  
});  

消费者B

Destination destination = session.createQueue("order_queue");  
// 创建消费者  
MessageConsumer consumer = session.createConsumer(destination,"JMSXGroupID='B'");  
consumer.setMessageListener(new MessageListener() {  
 @Override  
 public void onMessage(Message message) {  
     TextMessage textMessage = (TextMessage) message;  
     try {  
         System.out.println("A："+textMessage.getText());  
     } catch (JMSException e) {  
         e.printStackTrace();  
     }  
 }  
});

``` 


##### spring acvtivemq 练习 
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
