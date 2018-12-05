package com.tian.spring.zookeeper.zkclient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.*;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: ZookeeperClient
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午6:26
 */
@Slf4j
public class ZKClientMainTest {

    private static final String host = "127.0.0.1:2181";

    private static final String zkPath = "/spring-zookeeper";


    public static void main(String[] args) throws Exception {

        ZkClient client = new ZkClient(host);

        if (!client.exists(zkPath)) {
            client.create(zkPath, zkPath, CreateMode.PERSISTENT);
            client.subscribeChildChanges(zkPath, (s, list) -> {
                System.out.println("s: " + s + " list:" + list);
            });

            client.subscribeStateChanges(new IZkStateListener() {
                @Override
                public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {

                }

                @Override
                public void handleNewSession() throws Exception {

                }

                @Override
                public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

                }
            });

            client.subscribeDataChanges(zkPath, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                    System.out.println(" handleDataChange s: " + s + " list:" + o);
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    System.out.println(" handleDataDeleted s: " + s);
                }
            });

            client.writeData(zkPath, "hello world");
            Thread.sleep(2000);
            client.createEphemeralSequential(zkPath + "/", "asd");
            Thread.sleep(2000);
            client.createEphemeralSequential(zkPath + "/", "asd---2");
            Thread.sleep(2000);

            List<String> children = client.getChildren(zkPath);
            System.out.println(children);
        }
        client.deleteRecursive(zkPath);
//        client.delete(zkPath);
        Thread.sleep(2000);

    }


}
