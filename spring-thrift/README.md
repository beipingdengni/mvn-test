## mvn-test

``` text
Hello.thrift  文件
namespace java service.demo
service Hello{
    string helloString(1:string para)
}

命令生成
thrift -r -gen java Hello.thrift

```

#### 简单使用 服务选择 [具体介绍服务使用，点击查看](https://blog.csdn.net/sunmenggmail/article/details/46818147)
```text
数据类型
     * Base Types：基本类型
     * Struct：结构体类型
     * Container：容器类型，即List、Set、Map
     * Exception：异常类型
     * Service： 定义对象的接口，和一系列方法

协议
  Thrift可以让你选择客户端与服务端之间传输通信协议的类别，在传输协议上总体上划分为文本(text)和二进制(binary)传输协议, 为节约带宽，提供传输效率，一般情况下使用二进制类型的传输协议为多数，但有时会还是会使用基于文本类型的协议，这需要根据项目/产品中的实际需求：
    * TBinaryProtocol – 二进制编码格式进行数据传输。
    * TCompactProtocol – 这种协议非常有效的，使用Variable-Length Quantity (VLQ) 编码对数据进行压缩。
    * TJSONProtocol – 使用JSON的数据编码协议进行数据传输。
    * TSimpleJSONProtocol – 这种节约只提供JSON只写的协议，适用于通过脚本语言解析
    * TDebugProtocol – 在开发的过程中帮助开发人员调试用的，以文本的形式展现方便阅读。

传输层
    * TSocket- 使用堵塞式I/O进行传输，也是最常见的模式。
    * TFramedTransport- 使用非阻塞方式，按块的大小，进行传输，类似于Java中的NIO。
    * TFileTransport- 顾名思义按照文件的方式进程传输，虽然这种方式不提供Java的实现，但是实现起来非常简单。
    * TMemoryTransport- 使用内存I/O，就好比Java中的ByteArrayOutputStream实现。
    * TZlibTransport- 使用执行zlib压缩，不提供Java的实现。

服务端类型
    * TSimpleServer -  单线程服务器端使用标准的堵塞式I/O。
    * TThreadPoolServer -  多线程服务器端使用标准的堵塞式I/O。
    * TNonblockingServer – 多线程服务器端使用非堵塞式I/O，并且实现了Java中的NIO通道。
    * TThreadedSelectorServer - 非阻塞I/O【最优选择】

```
#### 基础使用
```text
server 使用
TProcessor processor = new BaseService.Processor<BaseService.Iface>(new BaseServiceImpl());
TServerSocket tServerSocket = new TServerSocket(50005);
TServer.Args tArgs = new TServer.Args(tServerSocket);
tArgs.processor(processor);
tArgs.protocolFactory(new TBinaryProtocol.Factory());
TServer server = new TSimpleServer(tArgs);
server.serve();

client 使用
TTransport    transport = new TSocket("localhost", 50005);
TProtocol protocol = new TBinaryProtocol(transport);
BaseService.Client client = new BaseService.Client(protocol);
transport.open();
// 调用方法
PersonVo result = client.dealPerson("liyao", "nan", 18);
System.out.println(result.toString());

```

#### thrift 最优选择 【俊明dubbo 中使用】
```text

server 服务端 优先选择

TProcessor tprocessor;
TThreadedSelectorServer.Args tArgs = null;
String iFace = "$Iface";
String processor = "$Processor";
String typeName = type.getName();
TNonblockingServerSocket transport;
if (typeName.endsWith(iFace)) {
String processorClsName = typeName.substring(0, typeName.indexOf(iFace)) + processor;
try {
    Class<?> clazz = Class.forName(processorClsName);
    Constructor constructor = clazz.getConstructor(type);
    try {
        tprocessor = (TProcessor) constructor.newInstance(impl);

        //解决并发连接数上限默认只有50的问题
        TNonblockingServerSocket.NonblockingAbstractServerSocketArgs args = new TNonblockingServerSocket.NonblockingAbstractServerSocketArgs();
        args.backlog(1000);//1k个连接
        args.port(url.getPort());
        args.clientTimeout(10000);//10秒超时

        transport = new TNonblockingServerSocket(args);

        tArgs = new TThreadedSelectorServer.Args(transport);
        tArgs.workerThreads(200);
        tArgs.selectorThreads(4);
        tArgs.acceptQueueSizePerThread(256);
        tArgs.processor(tprocessor);
        tArgs.transportFactory(new TFramedTransport.Factory());
        tArgs.protocolFactory(new TCompactProtocol.Factory());
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RpcException("Fail to create thrift server(" + url + ") : " + e.getMessage(), e);
    }
} catch (Exception e) {
    logger.error(e.getMessage(), e);
    throw new RpcException("Fail to create thrift server(" + url + ") : " + e.getMessage(), e);
}
}

if (tArgs == null) {
logger.error("Fail to create thrift server(" + url + ") due to null args");
throw new RpcException("Fail to create thrift server(" + url + ") due to null args");
}
final TServer thriftServer = new TThreadedSelectorServer(tArgs);

new Thread(new Runnable() {
public void run() {
    logger.info("Start Thrift ThreadedSelectorServer");
    thriftServer.serve();
    logger.info("Thrift ThreadedSelectorServer started.");
}
}).start();

client 客户端

try {
    TSocket tSocket;
    TTransport transport;
    TProtocol protocol;
    T thriftClient = null;
    String iFace = "$Iface";
    String client = "$Client";

    String typeName = type.getName();
    if (typeName.endsWith(iFace)) {
        String clientClsName = typeName.substring(0, typeName.indexOf(iFace)) + client;
        Class<?> clazz = Class.forName(clientClsName);
        Constructor constructor = clazz.getConstructor(TProtocol.class);
        try {
            tSocket = new TSocket(url.getHost(), url.getPort());
            transport = new TFramedTransport(tSocket);
            protocol = new TCompactProtocol(transport);
            thriftClient = (T) constructor.newInstance(protocol);
            transport.open();
            logger.info("thrift client opened for service(" + url + ")");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RpcException("Fail to create remote client:" + e.getMessage(), e);
        }
    }
    return thriftClient;
} catch (Exception e) {
    logger.error(e.getMessage(), e);
    throw new RpcException("Fail to create remote client for service(" + url + "): " + e.getMessage(), e);
}


```
