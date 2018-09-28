package com.tian.spring.netty.simple.client;

import com.alibaba.fastjson.JSON;
import com.tian.spring.netty.support.Request;
import com.tian.spring.netty.support.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
//        ctx.channel().close();
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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);

        System.out.println("连接 异常 ");

//        ctx.close();
    }
}