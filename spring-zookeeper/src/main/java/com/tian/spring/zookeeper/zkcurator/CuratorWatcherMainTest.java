package com.tian.spring.zookeeper.zkcurator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: CuratorWatcherMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/17下午2:13
 */
@Slf4j
public class CuratorWatcherMainTest {
    private static final String zookeeperConnectionString = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        curatorFramework.start();


        TreeCacheListener listener = new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                ChildData data = treeCacheEvent.getData();

                if (Objects.nonNull(data)) {
                    log.warn("开始测试,{}", data.getPath());
                } else {
                    log.warn("开始测试,{}", "null");
                }

                switch (treeCacheEvent.getType()) {
                    case NODE_ADDED:
                        log.warn("NODE_ADDED ");
                        break;
                    case NODE_REMOVED:
                        log.warn("NODE_REMOVED ");
                        break;
                    case NODE_UPDATED:
                        log.warn("NODE_UPDATED ");
                        break;
                    case INITIALIZED:
                        log.warn("INITIALIZED ");
                        break;
                    case CONNECTION_SUSPENDED:
                        log.warn("CONNECTION_SUSPENDED ");
                        break;
                    default:
                        log.warn("default {}", treeCacheEvent.getType());
                        break;

                }
            }
        };

        TreeCache treeCache = TreeCache.newBuilder(curatorFramework, "/spring.zookeeper").build();

        treeCache.getListenable().addListener(listener);

        treeCache.start();

        log.warn(" start ==== end ");


        while (true) {
            TimeUnit.SECONDS.sleep(10);
        }


    }
}
