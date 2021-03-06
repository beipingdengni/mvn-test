package com.tian.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tianbeiping
 * @Title: PersonMapper
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午6:08
 */
public interface PersonMapper {

    List<Map<String,Object>> selectPersonList(Map<String,Object> map);

    Integer insertPerson(Map<String,Object> map);

}
