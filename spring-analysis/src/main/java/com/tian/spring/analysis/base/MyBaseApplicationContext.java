package com.tian.spring.analysis.base;

import com.tian.spring.analysis.common.MyBaseCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author tianbeiping
 * @Title: MyBaseApplicationContext
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午1:53
 */
public class MyBaseApplicationContext implements ApplicationContextAware, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        logger.info("测试 数据 start  ====>  {}", "setApplicationContext()");
    }

    public void afterPropertiesSet() throws Exception {

        MyBaseCommon bean = applicationContext.getBean(MyBaseCommon.class);
        System.out.println(bean.testHello(" afterPropertiesSet() "));

        logger.info("测试 数据 ====>  {}", "afterPropertiesSet()");
    }

    public void sayHello(String str) {

        MyBaseCommon bean = applicationContext.getBean(MyBaseCommon.class);

        logger.info("sayHello ====>{}", bean.testHello(str));
    }
}
