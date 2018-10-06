package com.tian.spring.resteasy.service;

import com.tian.spring.resteasy.vo.UserVo;
import org.springframework.http.MediaType;

import javax.ws.rs.*;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: UserService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:19
 */
@Path("/user")
@Consumes("*/*")
@Produces("application/json;charset=UTF-8")
public interface UserService {

    @GET
    @Path("/{id}")
    UserVo get(@PathParam("id") String id);

    @GET
    @Path("/all")
    List<UserVo> all() throws Exception;

    @POST
    @Path("/add")
    UserVo add(UserVo user);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") String id);

}