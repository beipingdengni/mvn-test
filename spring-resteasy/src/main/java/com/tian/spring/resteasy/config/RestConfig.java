package com.tian.spring.resteasy.config;

import com.tian.spring.resteasy.impl.UserServiceImpl;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tianbeiping
 * @Title: Config
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午1:07
 */
public class RestConfig extends Application {


    HashSet<Object> singletons = new HashSet<Object>();

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        return set;
    }

    @Override
    public Set<Object> getSingletons() {

        singletons.add(new UserServiceImpl());

        return singletons;
    }
}
