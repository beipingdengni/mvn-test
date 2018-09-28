package com.tian.spring.netty.support;

import lombok.Data;

/**
 * @author tianbeiping
 * @Title: AbstractTranportData
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/28上午11:41
 */
@Data
public abstract class AbstractTransportData {

    private String dateNow;

    private String application;
    private String version;

    private String user;
    private String password;

    private String group;
    private String auth;

    private TransportData data;


    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public TransportData getData() {
        return data;
    }

    public void setData(TransportData data) {
        this.data = data;
    }
}
