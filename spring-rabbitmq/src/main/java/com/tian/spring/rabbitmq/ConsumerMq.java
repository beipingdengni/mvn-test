package com.tian.spring.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author tianbeiping
 * @Title: ConsumerMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/31下午5:08
 */
public class ConsumerMq {

    public static void main(String[] args) throws IOException, TimeoutException {

        String queue_name = "rabbit-mq-queue1";

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");

        connectionFactory.setPort(5672);

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();


        Channel channel = connection.createChannel();

        channel.queueDeclare(queue_name, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        //channel.basicQos(1);

        // param: queue_name autoAck consumer
        channel.basicConsume(queue_name, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        });


    }
}
