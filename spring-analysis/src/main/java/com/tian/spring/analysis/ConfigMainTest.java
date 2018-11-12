package com.tian.spring.analysis;

import com.tian.spring.analysis.config.AopConfig;
import com.tian.spring.analysis.services.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
public class ConfigMainTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        HelloService bean = context.getBean(HelloService.class);

        bean.div("name  tian");
        context.stop();
    }
}
