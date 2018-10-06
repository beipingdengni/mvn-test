package com.tiam.spring.undertow;

import com.google.common.collect.Lists;
import com.tiam.spring.undertow.service.UserServicesImpl;
import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
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
//        server.deploy(UndertowMainTest.class, "/");

        /**
         * 方式二 部署
         */
//        DeploymentInfo di = server.undertowDeployment(UndertowMainTest.class);
//        di.setContextPath("");
//        di.setDeploymentName("DI");
//        server.deploy(di);

        /**
         * 方式三 部署  not application
         */
        ResteasyDeployment deployment = new ResteasyDeployment();
        List<Class> resourceClass = Lists.newArrayList();
        resourceClass.add(UserServicesImpl.class);
        deployment.setActualResourceClasses(resourceClass);

        DeploymentInfo di = server.undertowDeployment(deployment);
        di.setClassLoader(UndertowMainTest.class.getClassLoader());
        di.setContextPath("/");
        di.setDeploymentName("Resteasy/");

        server.deploy(di);

    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(UserServicesImpl.class);
        return classes;
    }
}
