package com.tian.spring.ribbon.feign.provider;

import com.tian.spring.ribbon.feign.vo.UserVo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * @author tianbeiping
 * @Title: UserServiceProvider
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:50
 */
public interface UserServiceProvider {

    @Headers({"Content-Type: application/json;charset=utf-8", "Accept: application/json"})
    @RequestLine("POST /user/add")
    UserVo addUser(UserVo user);

    @RequestLine("GET /user/{id}")
//    @Body("a={a}&b={b}")// post 提交
    UserVo getUser(@Param("id") String name);


    @Headers({"Content-Type: application/json;charset=utf-8", "Accept: application/json"})
    @RequestLine("GET /user/all")
    List<UserVo> getUserAll();


}
