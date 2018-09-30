package com.tian.spring.ribbon.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author tianbeiping
 * @Title: BasicAuthRequestInterceptor
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午3:25
 */
public class SelfRequestInterceptor implements RequestInterceptor {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate == null) {
            System.out.println("RequestInterceptor  ====> 拦截器  null null  ");
        }

        if (requestTemplate.method().equalsIgnoreCase("POST")) {
            System.out.println("RequestInterceptor  ====> 拦截器  body  " + new String(requestTemplate.body()));
        }

        System.out.println("RequestInterceptor  ====> 拦截器 url " + requestTemplate.url());
    }
}
