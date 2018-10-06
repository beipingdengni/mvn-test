package com.tiam.spring.undertow.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author tianbeiping
 * @Title: UserServices
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30上午11:04
 */
@Path("user")
@Consumes("*/*")
@Produces("application/json;charset=utf-8")
public interface UserServices {

    @GET
    @Path("ping")
    String ping();
}
