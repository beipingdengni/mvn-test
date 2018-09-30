package com.tian.spring.resteasy.excaption;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author tianbeiping
 * @Title: NofoundExcatption
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午6:34
 */
public class NotFoundException implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        System.out.println("  " + e.getMessage());

        return Response.ok("Exception" + e.getMessage(), MediaType.APPLICATION_JSON_TYPE).status(Response.Status.NOT_FOUND).build();
    }
}
