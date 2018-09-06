package com.tian.spring.analysis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午1:52
 */
public class MainTest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
//        MyBaseApplicationContext bean = context.getBean(MyBaseApplicationContext.class);
//        bean.sayHello(" 测试数据类  ")

    }
}
