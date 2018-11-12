package com.tian.spring.kafka.config;

import com.google.common.collect.Maps;
import com.tian.spring.kafka.common.MyProducerListener;
import com.tian.spring.kafka.service.MyListener;
import com.tian.spring.kafka.service.ProducerService;
import org.checkerframework.checker.units.qual.K;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

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
@Import({ProducerService.class})
public class ProducerConfig {


    @Bean
    public Map<String, Object> producerPro() {
        Map<String, Object> props = Maps.newHashMap();
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
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerPro());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

//    @Bean
//    public DefaultKafkaProducerFactory defaultKafkaProducerFactory(Properties producerPro) {
//        return new DefaultKafkaProducerFactory(producerPro);
//    }
//
//
//    @Bean
//    public KafkaTemplate kafkaTemplate(DefaultKafkaProducerFactory defaultKafkaProducerFactory) {
//        KafkaTemplate kafkaTemplate = new KafkaTemplate(defaultKafkaProducerFactory, true);
//        kafkaTemplate.setDefaultTopic("mytopic");
//        kafkaTemplate.setProducerListener(new MyProducerListener());
//        return kafkaTemplate;
//    }

}
