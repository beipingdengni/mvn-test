package com.tiam.spring.undertow.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author tianbeiping
 * @Title: UserServicesImple
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30上午11:06
 */
public class UserServicesImpl implements UserServices {
    @Override
    public String ping() {

        try {
            InetAddress localHost = InetAddress.getLocalHost();

            String hostAddress = localHost.getHostAddress();

            String hostName = localHost.getHostName();

            return hostAddress + ":" + hostName;

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "ping";
    }
}
