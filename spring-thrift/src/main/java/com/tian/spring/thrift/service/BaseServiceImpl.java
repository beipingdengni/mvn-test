package com.tian.spring.thrift.service;

import com.tian.spring.thrift.vo.base.PersonVo._Fields;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.vo.base.PersonVo;
import org.apache.thrift.TException;

/**
 * @author tianbeiping
 * @Title: BaseService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午4:08
 */
public class BaseServiceImpl implements BaseService.Iface {
    public String ping() throws TException {

        return "ping";
    }

    public PersonVo dealPerson(String name, String sex, int age) throws TException {
        PersonVo personVo = new PersonVo();
        personVo.setName(name);
        personVo.setSex(sex);
        personVo.setAge(age);
        System.out.println(personVo.toString() + System.currentTimeMillis());
        return personVo;
    }
}
