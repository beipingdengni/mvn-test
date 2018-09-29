package com.tian.spring.resteasy.demo;

import javax.ws.rs.Consumes;
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
@Path("/user")
//@Produces("application/json;charset=utf-8")
//@Consumes("*/*")
public interface UserSvc {

    @GET
    @Path("/index")
    public String get();
}
