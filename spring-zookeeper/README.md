## mvn-test

```text

client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).creatingParentsIfNeeded().forPath(ZK_PATH, data1.getBytes());
print(client.getChildren().forPath("/"));
client.setData().forPath(ZK_PATH, data2.getBytes());
client.delete().forPath(ZK_PATH);

                
Path Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。产生的事件会传递给注册的PathChildrenCacheListener。
Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。
Tree Cache：Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。

```

#### 使用状态,watcher状态
```text
zookeeper：Watcher、ZK状态，事件类型（一）
zookeeper有watch事件，是一次性触发的，当watch监视的数据发生变化时，通知设置了该watch的client.即watcher.
同样：其watcher是监听数据发送了某些变化，那就一定会有对应的事件类型和状态类型。
	事件类型:(znode节点相关的)
		 EventType:NodeCreated            //节点创建
		 EventType:NodeDeleted            //节点删除
		 EventType:NodeDataChanged        //节点的数据变更
		 // 对应本代码而言只能监控根节点的一级节点变更。如：在根节点直接创建一级节点，
         //或者删除一级节点时触发。如修改一级节点的数据，不会触发，创建二级节点时也不会触发。
		 EventType:NodeChildrentChanged   //子节点下的数据变更
	状态类型:(是跟客户端实例相关的)
		 KeeperState:Disconneced        //连接失败
 		 KeeperState:SyncConnected	//连接成功	 
		 KeeperState:AuthFailed         //认证失败
		 KeeperState:Expired            //会话过期
```



