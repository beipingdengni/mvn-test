package com.tian.spring.zookeeper.zkclient;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author tianbeiping
 * @Title: ZookeeperClient
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午6:26
 */
public class ZookeeperClient implements Watcher {

    private static final String host = "127.0.0.1:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(host, 5000, new ZookeeperClient());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zooKeeper.exists("/spring.zookeeper/test1", true);
        zooKeeper.create("/spring.zookeeper/test1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.delete("/spring.zookeeper/test1", -1);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event: " + watchedEvent);
        System.out.println("watchedEvent Type : " + watchedEvent.getType());
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("连接到zookeeper");
        }
        countDownLatch.countDown();
    }
}
