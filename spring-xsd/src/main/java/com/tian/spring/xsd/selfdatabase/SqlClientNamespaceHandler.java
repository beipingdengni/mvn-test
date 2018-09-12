package com.tian.spring.xsd.selfdatabase;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author tianbeiping
 * @Title: SqlClientNamespaceHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午10:39
 */

public class SqlClientNamespaceHandler extends NamespaceHandlerSupport {
    public SqlClientNamespaceHandler() {
    }

    public void init() {
        this.registerBeanDefinitionParser("database", new SelfClientSqlBeanDefinitionParser());
    }
}
