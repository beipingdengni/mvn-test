package com.tian.spring.activemq.mwee;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.pool.PooledConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author tianbeiping
 * @Title: ConsumerMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/4/19下午3:11
 */
public class ProduceMain {


    public static void main(String[] args) throws JMSException {

//        String URL_CON = "failover:(tcp://10.1.25.247:61616,tcp://10.1.25.248:61616)?jms.useAsyncSend=true&randomize=false&backup=true&jms.prefetchPolicy.queuePrefetch=100";
        String URL_CON = "failover:(tcp://127.0.0.1:61616)?jms.useAsyncSend=true&randomize=false&backup=true&jms.prefetchPolicy.queuePrefetch=100";

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setMaxThreadPoolSize(100);
        connectionFactory.setBrokerURL(URL_CON);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 5000, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100000));

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Queue test_queue = session.createQueue("test_queue");

//        MessageProducer producer = session.createProducer(test_queue);
//        for (int x = 0; x < 100; x++) {
//            System.out.println(Thread.currentThread().getName() + " == " + x);
//            producer.send(session.createTextMessage("123"));
//        }
//        session.commit();


        List<Future<Long>> ls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<Long> submit = threadPoolExecutor.submit(() -> {
                try {
                    long start = System.currentTimeMillis();
                    MessageProducer producer = session.createProducer(test_queue);
                    for (int x = 0; x < 1000; x++) {
                        System.out.println(Thread.currentThread().getName() + " == " + x);
                        producer.send(session.createTextMessage(Thread.currentThread().getName() + " == " + x));
                    }
                    long end = System.currentTimeMillis();
                    return end - start;
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return 0L;
            });

            ls.add(submit);
        }

        Integer integer = ls.stream().map(e -> {
            try {
                return e.get(100, TimeUnit.SECONDS).intValue();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return 0;
        }).reduce((a, b) -> a + b).orElse(0);

        System.out.println(integer);

        threadPoolExecutor.shutdown();


    }
}
