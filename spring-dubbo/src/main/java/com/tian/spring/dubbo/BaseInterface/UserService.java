package com.tian.spring.dubbo.BaseInterface;

import com.tian.spring.dubbo.vo.UserVo;

/**
 * @author tianbeiping
 * @Title: UserService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:47
 */
public interface UserService {

    UserVo createUser(String name, Integer age, Byte sex);
}
