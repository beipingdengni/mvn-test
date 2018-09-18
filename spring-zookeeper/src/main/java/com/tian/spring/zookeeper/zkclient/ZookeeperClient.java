package com.tian.spring.zookeeper.zkclient;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.warn("set data one start");
            zooKeeper.setData("/zk_path", "hello world".getBytes(), -1);
            log.warn("set data one end");

            // 再次使用监听，需要注册
            //zooKeeper.getData("/zk_path", true, new Stat());
            zooKeeper.exists("/zk_path", true);
            log.warn("set data two start ==========>");
            zooKeeper.setData("/zk_path", "hello world  ===>".getBytes(), -1);
            log.warn("set data two end ==========>");
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

        log.warn(" receive watched event:  :  ===> " + watchedEvent);
        log.warn(" watchedEvent Type :  ===> " + watchedEvent.getType());
        log.warn(" start  ===> " + watchedEvent.getPath());

        String logPrefix = "/";
        String path = watchedEvent.getPath();
        //获取事件的状态
        Event.KeeperState keeperState = watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();

        //如果是建立连接
        if (Event.KeeperState.SyncConnected == keeperState) {
            if (Event.EventType.None == eventType) {
                //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                log.warn("zk 建立连接");
                countDownLatch.countDown();
            }
        }
        // 创建节点
        else if (Event.EventType.NodeCreated == eventType) {
            System.out.println(logPrefix + "节点创建");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.exists(path, true);
        }
        // 更新节点
        else if (Event.EventType.NodeDataChanged == eventType) {
            System.out.println(logPrefix + "节点数据更新");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(logPrefix + "数据内容: ");
        }
        // 更新子节点
        else if (Event.EventType.NodeChildrenChanged == eventType) {
            System.out.println(logPrefix + "子节点变更");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(logPrefix + "子节点列表：");
        }
        // 删除节点
        else if (Event.EventType.NodeDeleted == eventType) {
            System.out.println(logPrefix + "节点 " + path + " 被删除");
        }
    }
}
