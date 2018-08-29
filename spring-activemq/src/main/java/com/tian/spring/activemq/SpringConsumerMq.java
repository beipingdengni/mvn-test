package com.tian.spring.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.*;

/**
 * @author tianbeiping
 * @Title: SpringConumerMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/28下午7:01
 */
public class SpringConsumerMq implements MessageListener {


    public static void main(String[] args) throws JMSException {


        ApplicationContext context = new ClassPathXmlApplicationContext("consumer-mq.xml");
        PooledConnectionFactory connectionFactory = context.getBean(PooledConnectionFactory.class);

        ThreadPoolTaskExecutor taskExecutor = context.getBean("taskExecutor", ThreadPoolTaskExecutor.class);


        // 自定义消费者
        String[] queues = new String[]{"default-spring", "default", "queue1"};

        SpringConsumerMq mq = new SpringConsumerMq();
        // 开启多线程 监听
        for (int i = 0; i < queues.length; i++) {
            taskExecutor.execute(new ConsumerListener(connectionFactory, queues[i], mq), 3000);
        }


    }


    public static class ConsumerListener implements Runnable {

        private PooledConnectionFactory connectionFactory;
        private String queueName;

        private MessageListener messageListener;

        public ConsumerListener(PooledConnectionFactory connectionFactory, String queueName, MessageListener messageListener) {
            this.connectionFactory = connectionFactory;
            this.queueName = queueName;
            this.messageListener = messageListener;
        }

        public void run() {
            try {
                connectionFactory.setCreateConnectionOnStartup(false);
                connectionFactory.start();
                Connection connection = connectionFactory.createConnection();
                connection.start();

                Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                MessageConsumer consumer = session.createConsumer(new ActiveMQQueue(queueName));
                consumer.setMessageListener(messageListener);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void onMessage(Message message) {
        try {
            TextMessage message1 = (TextMessage) message;
            System.out.println(message1.getText());


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
