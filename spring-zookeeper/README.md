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



