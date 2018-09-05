package com.tian.spring.myabtis;

import com.tian.spring.myabtis.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
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

        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        List<Map<String, Object>> maps = mapper.selectPersonList();
        System.out.println(maps);


    }
}
