package com.tian.spring.netty.handler;

import java.util.Arrays;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: HelloHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/28下午2:31
 */
public class HelloHandler {


    public List<String> getPersonName() {

        return Arrays.asList("tian", "ping", "cai", "yang", "xie");
    }

}
