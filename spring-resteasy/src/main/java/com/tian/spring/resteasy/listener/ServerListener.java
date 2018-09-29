package com.tian.spring.resteasy.listener;

import com.tian.spring.resteasy.server.TomcatServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: ServerListener
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:34
 */
@Component
public class ServerListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        TomcatServer tomcatServer = new TomcatServer();
        tomcatServer.start();

    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
