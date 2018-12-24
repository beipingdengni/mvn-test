package com.tian.spring.mybatis.service;

import com.google.common.collect.Maps;
import com.tian.spring.mybatis.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianbeiping
 * @Title: UserService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/12/21下午2:10
 */
@Component
public class UserService {


    @Autowired
    private PersonMapper personMapper;

    @Transactional
    public void insertPerson() {
        HashMap<String, Object> data = Maps.newHashMap();

        List<Map<String, Object>> maps = personMapper.selectPersonList(data);
        System.out.println(maps);

        data.put("name", "tian==2");
        data.put("age", 18);
        data.put("pwd", "secrit");
        Integer integer = personMapper.insertPerson(data);

        System.out.println(integer);
    }


}
