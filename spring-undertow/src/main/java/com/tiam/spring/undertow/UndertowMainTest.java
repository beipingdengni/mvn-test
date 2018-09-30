package com.tiam.spring.undertow;

import com.google.common.collect.Maps;
import com.tiam.spring.undertow.service.UserServicesImpl;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.util.Headers;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tianbeiping
 * @Title: UndertowMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30上午11:03
 */
public class UndertowMainTest extends Application {


    public static void main(String[] args) {

        Undertow.Builder builder = Undertow.builder().addHttpListener(8080, "localhost");

        UndertowJaxrsServer server = new UndertowJaxrsServer().start(builder);

        /**
         * 方式一 部署
         */
        server.deploy(UndertowMainTest.class, "/");

        /**
         * 方式二 部署
         */
//        DeploymentInfo di = server.undertowDeployment(UndertowMainTest.class);
//        di.setContextPath("");
//        di.setDeploymentName("DI");
//        server.deploy(di);

    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(UserServicesImpl.class);
        return classes;
    }
}
