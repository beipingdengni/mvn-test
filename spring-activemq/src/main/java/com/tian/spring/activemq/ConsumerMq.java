package com.tian.spring.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author tianbeiping
 * @Title: ConsumerMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/28下午5:22
 */
public class ConsumerMq {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("default");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {

                TextMessage msg = (TextMessage) message;
                try {
                    String text = msg.getText();

                    System.out.println(text);


                } catch (JMSException e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
