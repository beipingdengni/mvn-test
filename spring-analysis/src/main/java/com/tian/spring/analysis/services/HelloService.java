package com.tian.spring.analysis.services;

import org.springframework.stereotype.Component;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/11/12 22:25
 */
@Component
public class HelloService {


    public void div(String name) {
        System.out.println("HelloService====>" + name);
        int s = 10 / 0;
    }
}
