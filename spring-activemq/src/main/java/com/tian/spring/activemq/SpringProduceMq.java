package com.tian.spring.activemq;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author tianbeiping
 * @Title: SpringProduceMq
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/28下午6:39
 */
public class SpringProduceMq {

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("produce-mq.xml");

        JmsTemplate bean = context.getBean(JmsTemplate.class);

        for (int i = 0; i < 100; i++) {
            bean.convertAndSend("default-spring", "spring-default ---- queue active mq ===> " + i);
            bean.convertAndSend("queue1", "queue1 ---- queue active mq ===>" + i);
            bean.convertAndSend("default ---- queue active mq ===>");
        }

    }
}
