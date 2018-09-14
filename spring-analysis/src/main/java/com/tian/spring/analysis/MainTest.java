package com.tian.spring.analysis;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: AnnotatedGenericBeanDefinitionMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10上午10:29
 */
@Component
public class MainTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");



    }
}
