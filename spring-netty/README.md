## spring-netty

```text

// netty 序列化

jboss-marshalling-1.3.0.CR9.jar
jboss-marshalling-serial-1.3.0.CR9.jar


netty为protobuf提供了两个编码器（ProtobufEncoder，ProtobufVarint32LengthFieldPrepender），两个解码器（ProtobufVarint32FrameDecoder，ProtobufDecoder）
//解码用
p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
//构造函数传递要解码成的类型
p.addLast("protobufDecoder", new ProtobufDecoder(LocalTimeProtocol.LocalTimes.getDefaultInstance()));
//编码用
p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());


//http

//server
// server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
ch.pipeline().addLast(new HttpResponseEncoder());
// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
ch.pipeline().addLast(new HttpRequestDecoder());
// 消息聚合器（重要）
ch.addLast("aggregator", new HttpObjectAggregator(512 * 1024))

//client
// 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
ch.pipeline().addLast(new HttpResponseDecoder());
// 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
ch.pipeline().addLast(new HttpRequestEncoder());
// 消息聚合器（重要）
ch.addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3

```
