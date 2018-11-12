package com.tian.spring.analysis.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/11/12 22:26
 */
@Aspect
@Component
public class AopHello {


    @Pointcut("execution(* com.tian.spring.analysis.services.HelloService.*(..))")
    private void pointcut() {
    }

    @Before("pointcut()&&args(name)")
    private void after(JoinPoint joinPoint, String name) {
        System.out.println("poit ====>" + name);
    }


    @AfterThrowing(value = "pointcut()", throwing = "ex")
    private void throwName(JoinPoint joinPoint, Exception ex) {
        System.out.println("poit ====>" + ex.getMessage());
    }

}
