## mvn-test

#### people.xsd 新建文件
```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <xsd:schema xmlns="http://www.tian.com/spring/schema/people"
               xmlns:xsd="http://www.w3.org/2001/XMLSchema"
               xmlns:beans="http://www.springframework.org/schema/beans"
               targetNamespace="http://www.tian.com/spring/schema/people"
               elementFormDefault="qualified"
               attributeFormDefault="unqualified">
       <xsd:import namespace="http://www.springframework.org/schema/beans" />
</xsd:schema>       
```
#### spring.handlers 新建文件
```text
http\://www.tian.com/spring/schema/people=com.tian.spring.sechema.StudentNamespaceHandler
```

#### spring.schemas 新建文件
```text
http\://www.tian.com/spring/schema/people.xsd=META-INF/people.xsd
```

#### PeopleBeanDefinitionParser.java   people.xsd文件解析 
```java
public class PeopleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    protected Class getBeanClass(Element element) {
        return Student.class;
    }
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String name = element.getAttribute("name");
        if (StringUtils.hasText(name)) {
            bean.addPropertyValue("name", name);
        }
    }
}
```

#### StudentNamespaceHandler.java   文件解析注入 
```java
public class StudentNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("student", new PeopleBeanDefinitionParser());
    }
}
```

#### application.xml 新建文件
```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:people="http://www.tian.com/spring/schema/people"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
       http://www.tian.com/spring/schema/people http://www.tian.com/spring/schema/people.xsd" >

    <people:student id="student1" name="student1" age="18" />
    
</beans>
```

#### 动态代理接口 无实现类使用
``
```
#### 代理类实现
```java
   
```

