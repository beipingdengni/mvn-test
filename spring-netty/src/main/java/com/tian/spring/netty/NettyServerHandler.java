package com.tian.spring.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

/**
 * @author tianbeiping
 * @Title: NettyServerHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:03
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 收到消息时，返回信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 收到消息直接打印输出
        System.out.println("服务端接受的消息 : " + msg);
        //服务端断开的条件
        if ("quit".equals(msg)) {
            ctx.close();
        }
        Date date = new Date();
        // 返回客户端消息
        ctx.writeAndFlush(date + "\n");
    }

    /**
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ \n");
        super.channelActive(ctx);
    }
}