package com.tian.spring.resteasy.demo;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tianbeiping
 * @Title: UserSvcApp
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午1:25
 */
@Provider
public class UserSvcApp extends Application {


    HashSet<Object> singletons = new HashSet<Object>();

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        set.add(UserSvc.class);
        return set;
    }

    @Override
    public Set<Object> getSingletons() {

        singletons.add(new UserSvc());

        return singletons;
    }
}
