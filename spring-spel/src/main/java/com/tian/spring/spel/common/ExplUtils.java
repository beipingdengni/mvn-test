package com.tian.spring.spel.common;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ExplUtils {


    public static String generateKey(String key, StandardEvaluationContext context) {

        BeanFactory beanFactory = SpringBeanUtils.getBeanFactory();
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(key);
        String value = exp.getValue(context,String.class);
        return value;
    }


}
