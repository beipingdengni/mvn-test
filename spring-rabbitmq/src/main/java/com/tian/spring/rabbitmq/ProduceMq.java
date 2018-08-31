package com.tian.spring.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author tianbeiping
 * @Title: ProduceMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/31下午5:08
 */
public class ProduceMq {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        String queue_name = "rabbit-mq-queue1";

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");

        connectionFactory.setPort(5672);

        connectionFactory.setUsername("guest");

        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // String queue,
        // boolean durable,
        // boolean exclusive(独家),
        // boolean autoDelete(是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除),
        // Map<String, Object> arguments
        channel.queueDeclare(queue_name, false, false, false, null);

        while (true) {
            for (int i = 0; i < 100; i++) {

                String result = "上海很美丽===> hello world  " + i;

                //param String exchange, String routingKey, BasicProperties props, byte[] body
                channel.basicPublish("", queue_name, null, result.getBytes());
            }
            TimeUnit.SECONDS.sleep(10);
        }

        //channel.close();
        //connection.close();


    }
}
