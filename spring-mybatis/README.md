## mvn-test

#### sql 数据库时区
```sql
   # 对比现在时间，查看市区是否正确
   select now();
   # 查看配置   
   show variables like '%time_zone%';
   # 设置市区 
   set global time_zone = '+8:00';
   flush privileges;
```
数据库连接
jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&&serverTimezone=UTC

#### mybatis 简单的代码调用
```java
public class MybatisMainTest {
    public static void main(String[] args) {
    try{
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader, "development");、
            SqlSession sqlSession = factory.openSession();
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            List<Map<String, Object>> maps = mapper.selectPersonList();
            System.out.println(maps);
        }catch (Exception e){
            
        }finally{
            sqlSession.commit();
            sqlSession.close();
        }
    }
}

```

#### mapper.xml 配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.tian.spring.mybatis.mapper.PersonMapper">
    <!--<resultMap id="BaseResultMap" type="cn.mwee.service.entity.timer.JobEntity">-->
    <!--&lt;!&ndash;-->
    <!--WARNING - @mbggenerated-->
    <!--&ndash;&gt;-->
    <!--<id column="ID" property="id" jdbcType="INTEGER"/>-->
    <!--<result column="JobName" property="jobname" jdbcType="VARCHAR"/>-->
    <!--<result column="ContentID" property="contentid" jdbcType="INTEGER"/>-->
    <!--</resultMap>-->
</mapper>

```
####  mybatis数据库配置
```xml
<configuration>
    <properties resource="mysql-config.properties"/>
    <settings>
        <setting name="logImpl" value="LOG4J2"/>
    </settings>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--<typeAliases>-->
    <!--<package name="cn.mwee.service.entity"/>-->
    <!--</typeAliases>-->
    <mappers>
        <!--<mapper resource="src/main/resources/mybatis/person.xml"/>-->
        <mapper resource="mybatis/person.xml"/>
    </mappers>
    <!--<plugins>-->
    <!--&lt;!&ndash;下面的参数详解见http://git.oschina.net/free/Mybatis_PageHelper/blob/master/wikis/HowToUse.markdown&ndash;&gt;-->
    <!--分页插件-->
    <!--<plugin interceptor="com.github.pagehelper.PageHelper">-->
    <!--<property name="dialect" value="mysql"/>-->
    <!--<property name="reasonable" value="true"/>-->
    <!--<property name="offsetAsPageNum" value="true"/>-->
    <!--<property name="rowBoundsWithCount" value="true"/>-->
    <!--<property name="pageSizeZero" value="true"/>-->
    <!--</plugin>-->
    <!--tx.mybatis.mapper-->
    <!--<plugin interceptor="tk.mybatis.mapper.mapperhelper.MapperInterceptor">-->
    <!--<property name="mappers" value="tk.mybatis.mapper.common.Mapper"/>-->
    <!--<property name="IDENTITY" value="MYSQL"/>-->
    <!--<property name="notEmpty" value="true"/>-->
    <!--</plugin>-->
    <!--</plugins>-->
</configuration> 
```
#### mybatis-config
``` xml
   
```

