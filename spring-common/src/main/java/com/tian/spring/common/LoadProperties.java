package com.tian.spring.common;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: LoadProperties
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/20下午5:58
 */
public class LoadProperties {

    public static void main(String[] args) {


        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        PersonVo bean = context.getBean(PersonVo.class);
        System.out.println(bean);

        PropertyPlaceholderConfigurer pro = context.getBean(PropertyPlaceholderConfigurer.class);




    }

}
