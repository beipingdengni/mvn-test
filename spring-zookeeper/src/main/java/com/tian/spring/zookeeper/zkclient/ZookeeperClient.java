package com.tian.spring.zookeeper.zkclient;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
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
public class ZookeeperClient implements Watcher {

    private static final String host = "127.0.0.1:2181";

    private static final String zkPath = "/spring.zookeeper";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(host, 5000, new ZookeeperClient());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Stat exists = zooKeeper.exists("/zk_path", false);
        if (null != exists) {
            zooKeeper.delete("/zk_path", -1);
            zooKeeper.create("/zk_path", "开始创建根目录节点".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.exists("/zk_path", true);
            zooKeeper.getChildren("/zk_path", true);

            zooKeeper.setData("/zk_path", "hello world".getBytes(), -1);
        }

        Stat exists1 = zooKeeper.exists("/zk_path/ephemeral", false);
        if (exists1 == null) {
            String s = zooKeeper.create("/zk_path/ephemeral", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.exists(s, true);
            System.out.println(s);

            byte[] data = zooKeeper.getData(s, false, new Stat());
            System.out.println(" getdata " + new String(data));

            zooKeeper.delete(s, -1);
        }

        while (true) {
            TimeUnit.SECONDS.sleep(100);
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive watched event: " + watchedEvent);
        System.out.println("watchedEvent Type : " + watchedEvent.getType());
        System.out.println("connect path  " + watchedEvent.getPath());
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("连接到zookeeper");
            countDownLatch.countDown();
        }
    }
}
