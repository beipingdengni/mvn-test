package com.tian.spring.dubbo.http;

import com.tian.spring.dubbo.BaseInterface.UserService;
import com.tian.spring.dubbo.vo.UserVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author tianbeiping
 * @Title: HttpUserService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午1:48
 */

@Path("user")
@Produces("application/json;charset=utf-8")
@Consumes("*/*")
public interface HttpUserService {

    @Path("index")
    @GET
    UserVo createUser();

}
