package com.tian.spring.analysis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MyDefinitionBeanPostProcess
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/13下午4:13
 */
@Component
public class MyDefinitionBeanPostProcess implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        logger.info("postProcessBeforeInitialization  beanName 11 :{}", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization  beanName 22 :{}", beanName);
        return bean;
    }
}
