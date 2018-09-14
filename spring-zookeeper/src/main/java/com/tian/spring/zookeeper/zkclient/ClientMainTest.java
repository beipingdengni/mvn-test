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
public class ClientMainTest {

    private static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        String connect_addr = "127.0.0.1:2181";

        ZooKeeper zooKeeper = new ZooKeeper(connect_addr, 50000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

                log.info(" start  ===> " + watchedEvent.getPath());

                log.info(" evenType ===> " + watchedEvent.getType().name());

                if (watchedEvent.getType() == Event.EventType.NodeCreated) {

                    System.out.println();
                }

                //获取事件的状态
                Event.KeeperState keeperState = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();
                //如果是建立连接
                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        //如果建立连接成功，则发送信号量，让后续阻塞程序向下执行
                        connectedSemaphore.countDown();
                        log.info("zk 建立连接");
                    }
                }

            }
        });

        connectedSemaphore.await();
        log.info("开始启动");

        Stat exists = zooKeeper.exists("/spring.zookeeper", false);
        if (Objects.isNull(exists)) {
            String s = zooKeeper.create("/spring.zookeeper", "开始".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.info(s);
        }
        //删除节点  修改节点的值，-1表示跳过版本检查，其他正数表示如果传入的版本号与当前版本号不一致，则修改不成功，删除是同样的道理
        //zk.delete("/testRoot/children", -1);

        zooKeeper.register(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

                log.info(" register start  ===> " + watchedEvent.getPath());

                log.info(" register evenType ===> " + watchedEvent.getType().name());

            }
        });

        while (true){
            TimeUnit.SECONDS.sleep(10);
        }

    }

}
