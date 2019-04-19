package com.tian.spring.activemq.mwee;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tianbeiping
 * @Title: ConsumerMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/28下午5:22
 */
public class ConsumerMain {

    public static void main(String[] args) throws JMSException {
//        String URL_CON = "failover:(tcp://10.1.25.247:61616,tcp://10.1.25.248:61616)?jms.useAsyncSend=true&randomize=false&backup=true&jms.prefetchPolicy.queuePrefetch=100";
        String URL_CON = "failover:(tcp://127.0.0.1:61616,)?jms.useAsyncSend=true&randomize=false&backup=true&jms.prefetchPolicy.queuePrefetch=100";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL_CON);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test_queue");


        AtomicInteger a = new AtomicInteger();

        for (int i = 0; i < 30; i++) {

            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(message -> {

//                long start = System.currentTimeMillis();
                TextMessage msg = (TextMessage) message;
                try {
                    String text = msg.getText();
                    System.out.println(text);
//                    TimeUnit.MILLISECONDS.sleep( 150);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                long end = System.currentTimeMillis();

//                int bt = (int) (end - start);
//                a.addAndGet(bt);

//                System.out.println(a.get());
            });

            System.out.println("consumer ====== " + i);
        }


    }
}
