package com.tian.spring.mybatis;

import com.tian.spring.mybatis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

import java.util.Date;
import java.util.HashMap;
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


        Map<String, Object> map = new HashMap<>();
        map.put("age", "12");
        map.put("dateNow", new Date());
        List<Map<String, Object>> maps = mapper.selectPersonList(map);
        System.out.println(maps);


        sqlSession.commit();
        sqlSession.close();

    }
}
