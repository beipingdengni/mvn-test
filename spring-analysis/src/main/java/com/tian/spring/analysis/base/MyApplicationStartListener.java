package com.tian.spring.analysis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MyApplicationStartListener
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14上午10:48
 */
@Component
@Slf4j
public class MyApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        // 防止二次调用处理
        if (contextRefreshedEvent.getApplicationContext().getParent() != null) {
            return;
        }


        Object source = contextRefreshedEvent.getSource();
        log.info("event.getSource()  ==>{}", source.getClass().getName());

        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();


        log.info("测试数据===> {}", "MyApplicationStartListener");
    }

}
