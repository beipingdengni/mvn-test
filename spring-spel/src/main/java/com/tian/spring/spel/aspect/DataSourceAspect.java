package com.tian.spring.spel.aspect;


import com.tian.spring.spel.common.DataSource;
import com.tian.spring.spel.common.ExplUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class DataSourceAspect {


    @Around("@annotation(ds)")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint, DataSource ds) throws Throwable {

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext(proceedingJoinPoint.getArgs());

        StandardEvaluationContext standardEvaluationContext1 = setContextVariables(standardEvaluationContext, proceedingJoinPoint);

        String s = ExplUtils.generateKey(ds.argParam(), standardEvaluationContext1);


        System.out.println(s);

        Object proceed = proceedingJoinPoint.proceed();

        return proceed;
    }


    public StandardEvaluationContext setContextVariables(StandardEvaluationContext standardEvaluationContext, ProceedingJoinPoint joinPoint) {


        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String[] parameterNames = methodSignature.getParameterNames();


        if (args == null || args.length <= 0) {

            return standardEvaluationContext;

        }
        for (int i = 0; i < args.length; i++) {

            standardEvaluationContext.setVariable(parameterNames[i], args[i]);

        }

        return standardEvaluationContext;


    }


}
