## mvn-test

####  执行 客户端请求调用链条【ReferenceBean】
```text
生成代理对象

ReferenceBean  
	--> get() 
	--> init() 
	--> T createProxy(map) 
	--> invoker = refprotocol.refer(interfaceClass, urls.get(0))
	--> ProtocolListenerWrapper --> Invoker<T> refer(Class<T> type, URL url)
	--> ProtocolFilterWrapper  --> Invoker<T> refer(Class<T> type, URL url)
	--> QosProtocolWrapper --> Invoker<T> refer(Class<T> type, URL url)
	--> RegistryProtocol 
			--> Invoker<T> refer(Class<T> type, URL url) 
			--> Invoker<T> doRefer(Cluster cluster, Registry registry, Class<T> type, URL url)
			--> 
	--> RegistryDirectory --> subscribe(URL url)
	--> FailbackRegistry --> subscribe(URL url, NotifyListener listener)
	--> ZookeeperRegistry --> doSubscribe(url, listener)
	--> FailbackRegistry -->  notify(URL url, NotifyListener listener, List<URL> urls)
	--> FailbackRegistry --> doNotify(URL url, NotifyListener listener, List<URL> urls)
	--> AbstractRegistry --> notify(URL url, NotifyListener listener, List<URL> urls)
	--> RegistryDirectory --> notify(List<URL> urls)
	--> RegistryDirectory --> refreshInvoker(List<URL> invokerUrls)
	--> RegistryDirectory --> Map<String, Invoker<T>> toInvokers(List<URL> urls)
		[invoker = new InvokerDelegate<T>(protocol.refer(serviceType, url), url, providerUrl)
		先执行其中：protocol.refer(serviceType, url) 如下 ]   
		--> ProtocolListenerWrapper --> Invoker<T> refer(Class<T> type, URL url)
					--> new ListenerInvokerWrapper<T>(....)
		--> ProtocolFilterWrapper  --> Invoker<T> refer(Class<T> type, URL url)
					--> buildInvokerChain(protocol.refer(type, url),...) 执行其中 protocol.refer(type, url)
			--> QosProtocolWrapper --> Invoker<T> refer(Class<T> type, URL url)
			--> DubboProtocol --> invoker = new DubboInvoker<T>(serviceType, url, getClients(url), invokers)
		--> ProtocolFilterWrapper  --> Invoker<T> refer(Class<T> type, URL url)
				--> buildInvokerChain(protocol.refer(type, url),...) 构建拦截器连
				
	--> RegistryDirectory --> Map<String, Invoker<T>> toInvokers(List<URL> urls)、
	--> proxyFactory.getProxy(invoker) 

方法拦截强求远程调用	
	
InvokerInvocationHandler 
	--> Object invoke(Object proxy, Method method, Object[] args)
	--> MockClusterInvoker --> Result invoke(Invocation invocation)
	--> AbstractClusterInvoker --> Result invoke(final Invocation invocation)
	--> FailoverClusterInvoker --> Result doInvoke(Invocation invocation, final List<Invoker<T>> invokers, LoadBalance loadbalance)
		--> AbstractClusterInvoker 
				--> Invoker<T> select(LoadBalance loadbalance, Invocation invocation, List<Invoker<T>> invokers, List<Invoker<T>> selected)  
				-->  doSelect(LoadBalance loadbalance, Invocation invocation, List<Invoker<T>> invokers, List<Invoker<T>> selected)
	--> InvokerWrapper --> invoke
	--> ListenerInvokerWrapper --> invoke
	--> ProtocolFilterWrapper --> invoke
	--> ConsumerContextFilter --> invoke
	--> ProtocolFilterWrapper --> invoke  [FutureFilter ]
	
	--> AbstractInvoker --> invoke(Invocation inv)
	--> DubboInvoker --> doInvoke(final Invocation invocation)
	--> ReferenceCountExchangeClient  --> request(Object request, int timeout)
	--> HeaderExchangeClient --> request(Object request, int timeout)
	--> AbstractPeer --> send(Object message)
	--> AbstractClient --> send(Object message, boolean sent)
	--> NettyChannel --> send(Object message, boolean sent)
	--> ResponseFuture --> get(int timeout)

```

