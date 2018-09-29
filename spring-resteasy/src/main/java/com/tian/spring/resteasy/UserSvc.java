package com.tian.spring.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author tianbeiping
 * @Title: UserServer
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午1:18
 */
@Path("user")
public class UserSvc {

    @Path("get")
    @GET
    @Produces("text/plain")
    public String get() {
        return "ok";
    }

}
