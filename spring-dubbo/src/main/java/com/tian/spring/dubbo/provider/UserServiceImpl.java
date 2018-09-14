package com.tian.spring.dubbo.provider;

import com.tian.spring.dubbo.BaseInterface.UserService;
import com.tian.spring.dubbo.vo.UserVo;

/**
 * @author tianbeiping
 * @Title: UserServiceImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:49
 */
public class UserServiceImpl implements UserService {

    public UserVo createUser(String name, Integer age, Byte sex) {

        UserVo userVo = new UserVo();
        userVo.setName(name);
        userVo.setAge(age);
        userVo.setSex(sex);

        System.out.println("provider 创建了一个 user ==》" + userVo.toString());

        return userVo;
    }
}
