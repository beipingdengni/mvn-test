package com.tian.spring.analysis.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
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
    private void before(JoinPoint joinPoint, String name) {
        System.out.println("before  poit ====>" + name);
    }


    @After("pointcut()")
    private void after(JoinPoint joinPoint) {
        System.out.println("after poit ====>");
    }

    @AfterReturning(value = "pointcut()",returning = "ret")
    private void after(JoinPoint joinPoint,String ret) {
        System.out.println("AfterReturning  poit ====>");
    }


    @Around("pointcut()")
    private Object Around(JoinPoint joinPoint) throws Throwable {

        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint)joinPoint;
        System.out.println("Around before poit ====>");
        Object proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        System.out.println("Around after poit ====>");

        return proceed;
    }


    @AfterThrowing(value = "pointcut()", throwing = "ex")
    private void throwName(JoinPoint joinPoint, Exception ex) {
        System.out.println("poit ====>" + ex.getMessage());
    }

}
