package com.tian.spring.zookeeper.zkclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: ClientMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午4:01
 */
@Slf4j
public class ClientMainTest implements Watcher {

    private static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        String connect_addr = "127.0.0.1:2181";

        ZooKeeper zooKeeper = new ZooKeeper(connect_addr, 50000, new ClientMainTest());

        connectedSemaphore.await();
        log.info("开始启动");


        Stat exists = zooKeeper.exists("/spring.zookeeper", true);
        if (Objects.isNull(exists)) {
            String s = zooKeeper.create("/spring.zookeeper", "开始".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info(s);
        }
        //删除节点  修改节点的值，-1表示跳过版本检查，其他正数表示如果传入的版本号与当前版本号不一致，则修改不成功，删除是同样的道理
        //zk.delete("/testRoot/children", -1);
        //zk.setData(path, data.getBytes(), -1)
        //List<String> list=this.zk.getChildren( path, false );


        while (true) {
            TimeUnit.SECONDS.sleep(10);
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {


        log.info(" start  ===> " + watchedEvent.getPath());

        log.info(" evenType ===> " + watchedEvent.getType().name());

        String logPrefix = "/";

        String path = watchedEvent.getPath();

        //获取事件的状态
        Event.KeeperState keeperState = watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();

        connectedSemaphore.countDown();

        //如果是建立连接
        if (Event.KeeperState.SyncConnected == keeperState) {
            if (Event.EventType.None == eventType) {
                //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                log.info("zk 建立连接");
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
                Thread.sleep(3000);
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
