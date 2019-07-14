package com.tian.spring.spel.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringBeanUtils implements BeanFactoryAware, ApplicationContextAware {


    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
