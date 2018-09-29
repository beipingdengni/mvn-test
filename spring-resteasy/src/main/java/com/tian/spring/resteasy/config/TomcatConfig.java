package com.tian.spring.resteasy.config;

import lombok.Data;

/**
 * @author tianbeiping
 * @Title: TomcatConfig
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:52
 */


public class TomcatConfig {

    private int port;
    private String contextPath;
    private int timeout;

}
