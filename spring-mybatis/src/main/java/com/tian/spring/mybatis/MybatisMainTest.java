package com.tian.spring.mybatis;

import com.tian.spring.mybatis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
<<<<<<< HEAD
import java.util.Arrays;
=======
import java.util.Date;
import java.util.HashMap;
>>>>>>> b9d5c00dcef6658ea9b9c38d8e9afee34f16603c
import java.util.List;
import java.util.Map;

/**
 * @author tianbeiping
 * @Title: MybatisMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/5下午4:11
 */
public class MybatisMainTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, "development");

        SqlSession sqlSession = factory.openSession();
//        List<Map<String, Object>> maps = sqlSession.selectList("com.tian.spring.mybatis.mapper.PersonMapper.selectPersonList");

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
<<<<<<< HEAD
        List<Map<String, Object>> maps = mapper.selectPersonList(Arrays.asList("123123","adsasdasd"));
=======

        Map<String, Object> map = new HashMap<>();
        map.put("age", "12");
        map.put("dateNow", new Date());
        List<Map<String, Object>> maps = mapper.selectPersonList(map);
>>>>>>> b9d5c00dcef6658ea9b9c38d8e9afee34f16603c
        System.out.println(maps);


        sqlSession.commit();
        sqlSession.close();

    }
}
