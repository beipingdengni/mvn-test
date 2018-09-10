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
1. 数据库连接
1. jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
1. serverTimezone=UTC+8    useSSL=true


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
#### create t_person demo 表
```sql
   DROP TABLE IF EXISTS `t_person`;
   CREATE TABLE `t_person` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `name` varchar(255) DEFAULT NULL comment '注解',
     `age` int(11) DEFAULT NULL,
     `pwd` varchar(255) DEFAULT NULL,
     `createDate` timestamp default current_timestamp() comment '默认时间'
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
   alter table t_user_remind add index I_SHONPID_USERID(D_SHOP_ID,D_USER_ID);

```
#### 数据库相关字节编码处理
```text

# 创建数据库，指定编码   mysql4.x 中编码  latin1   latin1_swedish_ci
# utf-8
CREATE DATABASE IF NOT EXISTS yourdbname DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
# gbk 
create database yourdb DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;
# 查看编码
show variables like 'character%';
   
sql语句数据库的字符集
mysql>use mydb
mysql>alter database mydb character set utf-8;
创建数据库指定数据库的字符集
mysql>create database mydb character set utf-8;
      
配置修改/var/lib/mysql/mydb/db.opt
default-character-set=latin1
default-collation=latin1_swedish_ci
为
default-character-set=utf8
default-collation=utf8_general_ci
重起MySQL:

mysql 相关操作
添加主键
ALTER TABLE table_name ADD PRIMARY KEY(id);
添加索引
//普通索引
alter table table_name add index index_name (column_list) ;
//唯一索引
alter table table_name add unique (column_list) ;
//主键索引 column_list 对个值，复合主键
alter table table_name add primary key (column_list) ;
删除索引
alter table table_name drop index index_name ;
// 删除主键
alter table table_name drop primary key ;

添加字段
alter table id_name add age int,add address varchar(11) DEFAULT NULL;
删除字段
alter table id_name drop column age,drop column address;
修改字段
alter table user MODIFY column_field_name VARCHAR(10); 
修改字段放置位置
ALTER TABLE user10 MODIFY card CHAR(10) AFTER test;
更新表，添加设置默认值
ALTER TABLE user11 ALTER age SET DEFAUTL 18;

修改表的存储引擎
ALTER TABLE user12 ENGINE=MyISAM;
ALTER TABLE user12 ENGINE=INNODB;

修改自增长的值
ALTER TABLE tb_name AUTO_INCREMENT=值

```

