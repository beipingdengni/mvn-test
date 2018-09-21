package com.tian.spring.common.excaption;

import lombok.Data;

/**
 * @author tianbeiping
 * @Title: EmailExcaption
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/21下午3:29
 */
@Data
public class EmailException extends Exception {


    private int errorCode;

    public EmailException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
