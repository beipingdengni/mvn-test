package com.tian.spring.jpa;

import com.tian.spring.jpa.entity.TPersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午3:19
 */
public class MainTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-hibernate.xml");
        //检查hibernate配置
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);


        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<TPersonEntity> personEntities = session.createQuery("from TPersonEntity", TPersonEntity.class).list();
        System.out.println(personEntities);

        tx.commit();
        session.close();
    }

}
