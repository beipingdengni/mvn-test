package com.tian.spring.xsd.people;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author tianbeiping
 * @Title: StudentNamespaceHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午11:09
 */
public class StudentNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("student", new PeopleBeanDefinitionParser());
    }
}
