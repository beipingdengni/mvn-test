package com.tian.spring.quartz.annotion;

import java.lang.annotation.*;

/**
 * @author tianbeiping
 * @Title: QuartzCron
 * @ProjectName mvn-test
 * @Description:
 * @date 18/8/30下午2:13
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface QuartzCron {

    String value() default "";


}
