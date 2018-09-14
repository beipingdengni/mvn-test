package com.tian.spring.dubbo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tianbeiping
 * @Title: UserVo
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:49
 */
@Data
public class UserVo implements Serializable {

    private String name;
    private Integer age;
    private Byte sex;

}
