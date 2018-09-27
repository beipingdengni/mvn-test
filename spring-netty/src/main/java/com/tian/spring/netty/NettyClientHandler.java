package com.tian.spring.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tianbeiping
 * @Title: NettyClientHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:06
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端接受的消息: " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取完成... ");
        super.channelReadComplete(ctx);
    }

    //
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("正在连接... ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭! ");
        super.channelInactive(ctx);
    }
}