package com.tian.spring.dubbo.http;

import com.tian.spring.dubbo.BaseInterface.UserService;
import com.tian.spring.dubbo.vo.UserVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tianbeiping
 * @Title: HttpUserServiceImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午1:52
 */

public class HttpUserServiceImpl implements HttpUserService {


    @Resource(name = "userService")
    private UserService userService;

    public UserVo createUser() {

        UserVo userVo = userService.createUser("田北平", 12, (byte) 3);
        return userVo;
    }
}
