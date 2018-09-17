package com.tian.spring.zookeeper.zkcurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/9/15 22:41
 */
public class CuratorMainTest {

    private static final String zookeeperConnectionString = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        curatorFramework.start();


        List<String> strings = curatorFramework.getChildren().forPath("/");


        System.out.println(strings);


        Stat stat = curatorFramework.checkExists().forPath("/spring.zookeeper/test");
        if (stat == null) {
            String s = curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/spring.zookeeper/test", "name".getBytes());
        }


        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/spring.zookeeper/test");
        if (lock.acquire(1, TimeUnit.SECONDS)) {
            try {
                // do some work inside of the critical section here
            } finally {
                lock.release();
            }
        }


        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
            }
        };

        LeaderSelector selector = new LeaderSelector(curatorFramework, "/spring.zookeeper/select", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();


    }
}
