package com.tian.spring.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * @author tianbeiping
 * @Title: ProduceMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/28下午5:21
 */
public class ProduceMq {

    public static void main(String[] args) throws JMSException {


        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setBrokerURL("tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);


        Queue queue = session.createQueue("default");
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 100; i++) {
            TextMessage txt = session.createTextMessage("让我看看海洋的美丽！" + "  " + i);
            producer.send(txt);
        }


        session.commit();

        connection.close();


    }
}
