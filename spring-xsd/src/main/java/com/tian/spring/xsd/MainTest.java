package com.tian.spring.xsd;

import com.tian.spring.xsd.selfdatabase.DatabaseClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午10:23
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-xsd.xml");
        DatabaseClient databaseClient = (DatabaseClient) ctx.getBean("student1");
        System.out.println(databaseClient.getDriver());
        System.out.println(databaseClient.getHost());
        System.out.println(databaseClient.getDbname());
    }
}
