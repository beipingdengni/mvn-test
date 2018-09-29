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
public interface UserService {
    String MEDIA_TYPE_JSON_UTF8 = MediaType.APPLICATION_JSON_UTF8_VALUE;

    @GET
    @Path("/{id}")
    @Produces(MEDIA_TYPE_JSON_UTF8)
    UserVo get(@PathParam("id") String id);

    @GET
    @Path("/all")
    @Produces(MEDIA_TYPE_JSON_UTF8)
    List<UserVo> all() throws Exception;

    @POST
    @Path("/add")
    @Produces(MEDIA_TYPE_JSON_UTF8)
    @Consumes(MEDIA_TYPE_JSON_UTF8)
    UserVo add(UserVo user);

    @DELETE
    @Path("/{id}")
    @Produces(MEDIA_TYPE_JSON_UTF8)
    void delete(@PathParam("id") String id);

}