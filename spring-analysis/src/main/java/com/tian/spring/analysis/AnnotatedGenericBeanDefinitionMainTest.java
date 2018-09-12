package com.tian.spring.analysis;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: AnnotatedGenericBeanDefinitionMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10上午10:29
 */
@Component
public class AnnotatedGenericBeanDefinitionMainTest {

    public static void main(String[] args) {

        AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(AnnotatedGenericBeanDefinitionMainTest.class);

        System.out.println(beanDefinition.getMetadata().getAnnotationTypes());
        System.out.println(beanDefinition.isSingleton());
        System.out.println(beanDefinition.getBeanClassName());

        String[] strings = beanDefinition.attributeNames();

    }
}
