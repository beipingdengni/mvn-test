package com.tian.spring.kafka.service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author tianbeiping
 * @Title: DaoService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午2:45
 */
@Service
@Slf4j
public class ProducerService {

    @Autowired
    private KafkaTemplate template;

    public void printName(String topic, String json) {
        
        log.info("json+++++++++++++++++++++  message = {}", json);

        ListenableFuture<SendResult<String, String>> future = template.send(topic, json);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("msg OK." + result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("msg send failed: ");
            }
        });
    }

}
