package com.tian.spring.analysis.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author tianbeiping
 * @Title: MybaseBeanDefinedRegister
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午3:32
 */
@Component
public class MybaseBeanDefinedRegister implements BeanDefinitionRegistryPostProcessor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        String basePackage = "com.tian.spring.analysis.dao";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.scan(StringUtils.tokenizeToStringArray(basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));

        logger.info("MybaseBeanDefinedRegister ===>  postProcessBeanDefinitionRegistry 111111");

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {

        logger.info("MybaseBeanDefinedRegister ===>  postProcessBeanFactory 222222");

    }
}
