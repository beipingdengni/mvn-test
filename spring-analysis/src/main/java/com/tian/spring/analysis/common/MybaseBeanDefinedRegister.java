package com.tian.spring.analysis.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: MybaseBeanDefinedRegister
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午3:32
 */
@Component
public class MybaseBeanDefinedRegister implements BeanDefinitionRegistryPostProcessor {
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathBeanDefinitionScanner scanner=new ClassPathBeanDefinitionScanner(registry);

        scanner.scan("com.tian.spring.analysis.dao");

    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {


    }
}
