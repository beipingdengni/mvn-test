package com.tian.spring.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author tianbeiping
 * @Title: MyListener
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午4:23
 */
@Component
@Slf4j
public class MyListener {


    @KafkaListener(topicPattern = "topic.*")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("------------------ message =topic：" + topic + ", " + message);
        }
    }

}
