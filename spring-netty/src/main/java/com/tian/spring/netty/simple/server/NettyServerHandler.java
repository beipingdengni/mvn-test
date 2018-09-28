package com.tian.spring.netty.simple.server;

import com.alibaba.fastjson.JSON;
import com.tian.spring.netty.proxy.JDKProxy;
import com.tian.spring.netty.service.HelloService;
import com.tian.spring.netty.service.HelloServiceImpl;
import com.tian.spring.netty.support.Request;
import com.tian.spring.netty.support.Response;
import com.tian.spring.netty.support.TransportData;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author tianbeiping
 * @Title: NettyServerHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:03
 */
//@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private Object proxyClass;

    public NettyServerHandler(Object proxyClass) {
        this.proxyClass = proxyClass;
    }

    /**
     * 收到消息时，返回信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        Request request = JSON.parseObject(msg, Request.class);
        TransportData data = request.getData();

        String methodName = data.getMethodName();
        Class<?>[] argumentTypes = data.getArgumentTypes();
        Object[] argumentData = data.getArgumentData();
        // 反射
        Class<?> aClass = proxyClass.getClass();
        Method method = aClass.getMethod(methodName, argumentTypes);
        Object invoke = method.invoke(proxyClass, argumentData);


        Response response = new Response();
        BeanUtils.copyProperties(request, response);

        response.setApplication("client-app-server");
        response.setAuth("server");
        response.setDateNow(localDateTime.format(formatter));
        response.getData().setResult(invoke);

        String s = JSON.toJSONString(response);
        System.out.println("server response===> " + s);

        // 返回客户端消息
        ctx.writeAndFlush(s);
    }

    /**
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
//        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ \n");
        super.channelActive(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接客户端断开:" + ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }
}