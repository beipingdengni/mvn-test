package com.tian.spring.netty.support;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author tianbeiping
 * @Title: TranportData
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/28上午11:40
 */
@Data
public class TransportData {

    private String methodName;
    private String interfaceName;
    private Class<?>[] argumentTypes;
    private Object[] argumentData;
    private Object result;

    @JSONField(serialize = false, deserialize = false)
    private Method method;
}
