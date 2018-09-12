package com.tian.spring.xsd.selfdatabase;

import com.tian.spring.xsd.selfdatabase.DatabaseClient;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author tianbeiping
 * @Title: SelfClientSqlBeanDefinitionParser
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午10:42
 */
public class SelfClientSqlBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return DatabaseClient.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String driver = element.getAttribute("driver");
        String url = element.getAttribute("url");
        String userName = element.getAttribute("userName");
        String password = element.getAttribute("password");
        String host = element.getAttribute("host");
        String dbname = element.getAttribute("dbname");
        String characterEncoding = element.getAttribute("characterEncoding");

        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("driver",driver);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("url",url);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("userName",userName);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("password",password);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("host",host);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("dbname",dbname);
        }
        if (StringUtils.hasText(driver)){
            bean.addPropertyValue("characterEncoding",characterEncoding);
        }
    }
}
