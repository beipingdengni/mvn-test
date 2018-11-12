package com.tiam.spring.undertow;

import com.google.common.collect.Lists;
import com.tiam.spring.undertow.service.UserServicesImpl;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import java.util.List;


/**
 * @author tianbeiping
 * @Title: NettyMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:17
 */
public class NettyMainTest {

    public static void main(String[] args) {

        ResteasyDeployment deployment = new ResteasyDeployment();
        List<Class> resourceClass = Lists.newArrayList();
        resourceClass.add(UserServicesImpl.class);
        deployment.setActualResourceClasses(resourceClass);

        NettyJaxrsServer netty = new NettyJaxrsServer();
        netty.setDeployment(deployment);
        netty.setPort(8080);
        netty.setRootResourcePath("");
        netty.setSecurityDomain(null);
        netty.start();


    }
}
