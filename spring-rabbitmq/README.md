## mvn-test

### 最简单的一个队列生产使用
#### 生产着
```java
class ProduceMq{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
            String queue_name = "rabbit-mq-queue1";
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(queue_name, false, false, false, null);
            String result = "上海很美丽===> hello world  " + i;
            //param String exchange, String routingKey, BasicProperties props, byte[] body
            channel.basicPublish("", queue_name, null, result.getBytes());
            // 关闭
            channel.close();
            connection.close(); 
    }
}
```

#### 消费者
```java
class ProduceMq{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
            String queue_name = "rabbit-mq-queue1";
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("127.0.0.1");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(queue_name, false, false, false, null);
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
```

#### 简单的介绍
```text
//每次从队列获取的数量
channel.basicQos(1);
        
queueDeclare(String queue, 
            boolean durable, 
            boolean exclusive, 
            Map<String, Object> arguments);
            
queue: 队列名称
durable：是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
exclusive：是否排外的，有两个作用，一：当连接关闭时connection.close()该队列是否会自动删除；二：该队列是否是私有的private，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问会报异常：com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)一般等于true的话用于一个队列只能有一个消费者来消费的场景
autoDelete：是否自动删除，当最后一个消费者断开连接之后队列是否自动被删除，可以通过RabbitMQ Management，查看某个队列的消费者数量，当consumers = 0时队列就会自动删除
   
```

