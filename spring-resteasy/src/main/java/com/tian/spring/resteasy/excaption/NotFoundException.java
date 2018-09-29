package com.tian.spring.resteasy.excaption;

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
//    @Override
//    public Response toResponse(Throwable throwable) {
//
//        return Response.ok().build();
//    }

    @Override
    public Response toResponse(Exception e) {

        System.out.println("  "+e.getMessage());

        return Response.ok().build();
    }
}
