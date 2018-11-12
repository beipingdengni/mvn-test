package com.tian.spring.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:43
 */
public class ConsumerMainTest {


    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String,String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("message"));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(10));
            for(ConsumerRecord<String,String> record : records){
                System.out.println("offset=" + record.offset() + ",--key=" + record.key() + ",--value=" + record.value());
            }
        }

    }
}
