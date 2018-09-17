package com.tian.spring.zookeeper.zkcurator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: LeaderSelectorTest
 * @ProjectName mvn-test
 * @Description:  通过LeaderSelectorListener可以对领导权进行控制， 在适当的时候释放领导权，这样每个节点都有可能获得领导权。
 *                而LeaderLatch则一直持有leadership， 除非调用close方法，否则它不会释放领导权。
 * @date 18/9/17下午6:32
 */
public class LeaderSelectorTest {

    private static final String PATH = "/spring.zookeeper/leader";

    public static void main(String[] args) {

        List<LeaderSelector> selectors = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework client = getClient();
                clients.add(client);

                final String name = "client#" + i;
                LeaderSelector leaderSelector = new LeaderSelector(client, PATH, new LeaderSelectorListener() {
                    @Override
                    public void takeLeadership(CuratorFramework client) throws Exception {

                        System.out.println(name + ":I am leader.");
                        Thread.sleep(2000);
                    }

                    @Override
                    public void stateChanged(CuratorFramework client, ConnectionState newState) {

                    }
                });
                // autoRequeue()方法的调用确保此实例在释放领导权后还可能获得领导权。
                leaderSelector.autoRequeue();
                leaderSelector.start();
                selectors.add(leaderSelector);

            }
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }

            for (LeaderSelector selector : selectors) {
                CloseableUtils.closeQuietly(selector);
            }

        }
    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        return client;
    }
}