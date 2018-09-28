package com.tian.spring.netty.simple.client;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tian.spring.netty.proxy.JDKProxy;
import com.tian.spring.netty.service.HelloService;
import com.tian.spring.netty.service.HelloServiceImpl;
import com.tian.spring.netty.support.Request;
import com.tian.spring.netty.support.Response;
import com.tian.spring.netty.support.TransportData;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: NettyClient
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:06
 */
public class NettyClient {


    private static final Map<String, List<TransportData>> classMap = new ConcurrentHashMap<>();


    public static String host = "127.0.0.1";  //ip地址
    public static int port = 33440;          //端口
    /// 通过nio方式来接收连接和处理连接
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap b = new Bootstrap();
    private static ChannelFuture ch;

    private static Map<String, ChannelFuture> channel = new ConcurrentHashMap<>();
    private static String channelName = "defaultChannel";

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是    ServerBootstrap。
     **/
    public static void main(String[] args) throws Exception {
        System.out.println("客户端成功启动...");
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new NettyClientFilter());
        // 连接服务端
        ch = b.connect(host, port).sync();
        channel.put(channelName, ch);
        star();
//        ch.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
//        ch.addListener(ChannelFutureListener.CLOSE).sync().channel();
        ch.channel().closeFuture().sync();
        group.shutdownGracefully();

    }

    private static void star() throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(formatter);


        dealClass(HelloService.class);
        List<TransportData> transportData = classMap.get(HelloService.class.getName());
        Request request = new Request();
        request.setAuth("client");
        request.setApplication("client-app-client");
        request.setDateNow(format);
        request.setData(transportData.get(0));

        String str = JSON.toJSONString(request);
        System.out.println("客户端发送数据:" + str);

        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.sleep(10);
            getChannel().channel().writeAndFlush(str);
        }

    }

    public static ChannelFuture getChannel() throws InterruptedException {
        ChannelFuture channelFuture = channel.get(channelName);
        if (Objects.isNull(channelFuture)) {
            System.out.println("======> 创建完成");
            channelFuture = b.connect(host, port).sync();
            channel.put(channelName, channelFuture);
        }
        return channelFuture;
    }

    public static void dealClass(Class<?> aClass) {
        String interfaceName = aClass.getName();
        Method[] methods = aClass.getMethods();
        List<TransportData> ls = Lists.newArrayList();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            TransportData transportData = new TransportData();
            transportData.setInterfaceName(interfaceName);
            transportData.setMethodName(method.getName());
            transportData.setArgumentTypes(method.getParameterTypes());
            transportData.setMethod(method);
            transportData.setResult(method.getAnnotatedReturnType().getType());
            ls.add(transportData);

        }
        classMap.put(interfaceName, ls);
    }


}
