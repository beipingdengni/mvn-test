package com.tian.spring.resteasy.impl;

import com.google.common.collect.Lists;
import com.tian.spring.resteasy.service.UserService;
import com.tian.spring.resteasy.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: UserServiceImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:27
 */
@Service
public class UserServiceImpl implements UserService {

    private List<UserVo> list = Lists.newArrayList();


    public UserVo get(String id) {
        UserVo userVo = new UserVo();
        userVo.setId("1");
        userVo.setName("田");

        return userVo;//list.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(new UserVo());
    }

    public List<UserVo> all() throws Exception {
        List<UserVo> ls = Lists.newArrayList();
        UserVo userVo = new UserVo();
        userVo.setId("1");
        userVo.setName("田");
        ls.add(userVo);

//        throw  new Exception("测试错误");
        return ls;
    }

    public UserVo add(UserVo user) {
        list.add(user);
        return user;
    }

    public void delete(String id) {

    }
}
