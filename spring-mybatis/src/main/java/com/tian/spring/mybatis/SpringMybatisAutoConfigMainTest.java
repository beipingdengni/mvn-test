package com.tian.spring.mybatis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: SpringMybatisMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午1:22
 */
public class SpringMybatisAutoConfigMainTest {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-mybatis-pagehelper.xml");


    }

}
