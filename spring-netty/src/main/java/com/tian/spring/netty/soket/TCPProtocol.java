package com.tian.spring.netty.soket;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author tianbeiping
 * @Title: TCPProtocol
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/30下午6:38
 */
public interface TCPProtocol {

    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;

}
