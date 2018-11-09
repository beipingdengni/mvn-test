package com.tian.spring.kafka.config;

import com.google.common.collect.Maps;
import com.tian.spring.kafka.common.MyMessageListener;
import com.tian.spring.kafka.common.MyProducerListener;
import com.tian.spring.kafka.service.MyListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.Map;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: BaseConfig
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:43
 */

@Configuration
@EnableKafka
//@ComponentScan("com.tian.*")
@Import({MyListener.class})
public class ConsumerConfig {


    @Bean
    public Map<String, Object> consumerPro() {
        Map<String, Object> props = Maps.newHashMap();
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
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerPro());
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }


//    @Bean
//    public DefaultKafkaConsumerFactory defaultKafkaConsumerFactory(Properties consumerPro) {
//        return new DefaultKafkaConsumerFactory(consumerPro);
//    }
//
//
//    @Bean
//    public ContainerProperties containerPropertiesOrder() {
//        ContainerProperties containerProperties = new ContainerProperties("order_test_topic");
//        containerProperties.setMessageListener(new MyMessageListener());
//        return containerProperties;
//    }
//
//    @Bean
//    public ContainerProperties containerPropertiesOther() {
//        ContainerProperties containerProperties = new ContainerProperties("other_test_topic");
//        containerProperties.setMessageListener(new MyMessageListener());
//        return containerProperties;
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer containerOrder(DefaultKafkaConsumerFactory defaultKafkaConsumerFactory, ContainerProperties containerPropertiesOrder) {
//        return new KafkaMessageListenerContainer(defaultKafkaConsumerFactory, containerPropertiesOrder);
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer containerOther(DefaultKafkaConsumerFactory defaultKafkaConsumerFactory, ContainerProperties containerPropertiesOther) {
//        return new KafkaMessageListenerContainer(defaultKafkaConsumerFactory, containerPropertiesOther);
//    }


}
