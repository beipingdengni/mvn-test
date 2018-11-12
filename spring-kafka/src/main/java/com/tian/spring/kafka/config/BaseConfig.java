package com.tian.spring.kafka.config;

import com.tian.spring.kafka.common.MyMessageListener;
import com.tian.spring.kafka.common.MyProducerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: BaseConfig
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:43
 */

@Configuration
@ComponentScan("com.tian.*")
public class BaseConfig {


    @Bean
    public Properties producerPro() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 2);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    @Bean
    public DefaultKafkaProducerFactory defaultKafkaProducerFactory(Properties producerPro) {
        return new DefaultKafkaProducerFactory(producerPro);
    }


    @Bean
    public KafkaTemplate kafkaTemplate(DefaultKafkaProducerFactory defaultKafkaProducerFactory) {
        KafkaTemplate kafkaTemplate = new KafkaTemplate(defaultKafkaProducerFactory, true);
        kafkaTemplate.setDefaultTopic("mytopic");
        kafkaTemplate.setProducerListener(new MyProducerListener());
        return kafkaTemplate;
    }


    @Bean
    public Properties consumerPro() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }


    @Bean
    public DefaultKafkaConsumerFactory defaultKafkaConsumerFactory(Properties consumerPro) {
        return new DefaultKafkaConsumerFactory(consumerPro);
    }


    @Bean
    public ContainerProperties containerPropertiesOrder() {
        ContainerProperties containerProperties = new ContainerProperties("order_test_topic");
        containerProperties.setMessageListener(new MyMessageListener());
        return containerProperties;
    }

    @Bean
    public ContainerProperties containerPropertiesOther() {
        ContainerProperties containerProperties = new ContainerProperties("other_test_topic");
        containerProperties.setMessageListener(new MyMessageListener());
        return containerProperties;
    }

    @Bean
    public KafkaMessageListenerContainer containerOrder(DefaultKafkaConsumerFactory defaultKafkaConsumerFactory, ContainerProperties containerPropertiesOrder) {
        return new KafkaMessageListenerContainer(defaultKafkaConsumerFactory, containerPropertiesOrder);
    }

    @Bean
    public KafkaMessageListenerContainer containerOther(DefaultKafkaConsumerFactory defaultKafkaConsumerFactory, ContainerProperties containerPropertiesOther) {
        return new KafkaMessageListenerContainer(defaultKafkaConsumerFactory, containerPropertiesOther);
    }


}
