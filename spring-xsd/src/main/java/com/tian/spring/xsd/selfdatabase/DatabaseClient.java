package com.tian.spring.xsd.selfdatabase;

import lombok.Data;

/**
 * @author tianbeiping
 * @Title: SelfClientSql
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/11上午10:34
 */
@Data
public class DatabaseClient {

    private String driver;
    private String url;
    private String userName;
    private String password;

    private String host;
    private String dbname;

    private String characterEncoding;


}
