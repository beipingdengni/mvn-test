package com.tian.spring.xsd;

import com.tian.spring.xsd.people.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: PersonMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午11:12
 */
public class PersonMainTest {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Student people = (Student) ctx.getBean("student1");
        System.out.println(people.getId());
        System.out.println(people.getName());
        System.out.println(people.getAge());

    }

}
