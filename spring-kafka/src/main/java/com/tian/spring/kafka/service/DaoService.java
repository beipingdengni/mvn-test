package com.tian.spring.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author tianbeiping
 * @Title: DaoService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:45
 */
@Service
public class DaoService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void printName() {
        System.out.println(this.getClass().getName());

        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("order_test_topic", "1", "测试启动==》" + i);
        }
    }

}
