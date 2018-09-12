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

<<<<<<< HEAD
    List<Map<String,Object>> selectPersonList(List<String> ls);
=======
    List<Map<String,Object>> selectPersonList(Map<String,Object> map);
>>>>>>> b9d5c00dcef6658ea9b9c38d8e9afee34f16603c

}
