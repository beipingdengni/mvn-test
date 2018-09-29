package com.tian.spring.resteasy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:19
 */
public class MainTest {



    public static void main(String[] args) {

        String property = System.getProperty("java.io.tmpdir");
        System.out.println(property);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-resteasy.xml");

    }
}
