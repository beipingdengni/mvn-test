package com.tian.spring.jpa;

import com.tian.spring.jpa.services.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: JpaMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午4:09
 */
public class JpaMainTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jpa.xml");
        PersonService bean = ctx.getBean(PersonService.class);

        System.out.println(bean.getPerson());

    }
}
