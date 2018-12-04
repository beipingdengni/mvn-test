package com.tian.spring.zookeeper.zkclient;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
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
            client.unsubscribeChildChanges(zkPath, (s, list) -> {
                System.out.println(s);
                System.out.println(list);
            });

            client.writeData(zkPath, "hello world");
            client.createEphemeralSequential(zkPath+"/a","asd");

            List<String> children = client.getChildren(zkPath);
            System.out.println(children);
        }
        client.deleteRecursive(zkPath);
//        client.delete(zkPath);

    }


}
