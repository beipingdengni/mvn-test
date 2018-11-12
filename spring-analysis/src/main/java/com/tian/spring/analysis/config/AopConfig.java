package com.tian.spring.analysis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/11/12 22:24
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.tian.spring.analysis.services")
public class AopConfig {
}
