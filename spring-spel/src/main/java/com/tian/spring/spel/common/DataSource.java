package com.tian.spring.spel.common;


import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DataSource {

    String value() default "";

    String argParam() default "";
}
