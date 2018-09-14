package com.tian.spring.analysis.base;

import com.tian.spring.analysis.common.MyBaseCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MyBaseApplicationContext
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午1:53
 */
@Component
public class MyBaseApplicationContext implements ApplicationContextAware, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    public MyBaseApplicationContext() {
        logger.info("测试 数据 构造函数  ====>  {}", "MyBaseApplicationContext");
    }

    @Autowired
    private MyBaseCommon myBaseCommon;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        logger.info("测试 数据 start  ====>  {}", "setApplicationContext()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        MyBaseCommon bean = applicationContext.getBean(MyBaseCommon.class);
        System.out.println(bean.testHello(" afterPropertiesSet() "));

        logger.info("测试 数据 ====>  {}", "afterPropertiesSet()");
    }

    public void sayHello(String str) {

        logger.info("sayHello ====>{}", myBaseCommon.testHello(str));
    }
}
