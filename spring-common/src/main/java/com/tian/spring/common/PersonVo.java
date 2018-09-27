package com.tian.spring.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author tianbeiping
 * @Title: PersonVo
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/27下午1:12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonVo {


    private String name;

    private String address;


}
