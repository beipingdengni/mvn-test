package com.tian.spring.kafka.common;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author tianbeiping
 * @Title: MyProducerListener
 * @ProjectName mvn-test
 * @Description:
 * @date 18/11/9下午3:31
 */
public class MyProducerListener implements ProducerListener {
    private final Logger LOG = LoggerFactory.getLogger(MyProducerListener.class);


    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        LOG.info("==========kafka发送数据成功（日志开始）==========");
        LOG.info("----------topic:" + topic);
        LOG.info("----------partition:" + partition);
        LOG.info("----------key:" + key);
        LOG.info("----------value:" + value);
        LOG.info("----------RecordMetadata:" + recordMetadata);
        LOG.info("~~~~~~~~~~kafka发送数据成功（日志结束）~~~~~~~~~~");
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        LOG.info("==========kafka发送数据错误（日志开始）==========");
        LOG.info("----------topic:" + topic);
        LOG.info("----------partition:" + partition);
        LOG.info("----------key:" + key);
        LOG.info("----------value:" + value);
        LOG.info("----------Exception:" + exception);
        LOG.info("~~~~~~~~~~kafka发送数据错误（日志结束）~~~~~~~~~~");
        exception.printStackTrace();
    }
}
