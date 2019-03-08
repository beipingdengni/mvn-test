package com.tian.spring.common.pool.thrift;

/**
 * @author tianbeiping
 * @Title: ThriftServiceConfigItem
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:25
 */
public class ThriftServiceConfigItem {

    private String serverIP;
    private int serverPort;
    private int timeOut;

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
