package com.tian.spring.netty.simple.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author tianbeiping
 * @Title: NettyServer
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/27下午6:39
 */
public class NettyServer {

    private EventLoopGroup bossEvenLoopGroup = null;
    private EventLoopGroup workEvenLoopGroup = null;
    private int port = 33440;

    public NettyServer() throws InterruptedException {
        init();
    }

    public NettyServer(Integer port) throws InterruptedException {
        this.port = port;
        init();
    }


    private void init() throws InterruptedException {
        bossEvenLoopGroup = new NioEventLoopGroup();
        workEvenLoopGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossEvenLoopGroup, workEvenLoopGroup)
                .option(ChannelOption.SO_BACKLOG, 2 * 1024)
                .option(ChannelOption.SO_RCVBUF, 2 * 1024)
                .option(ChannelOption.SO_SNDBUF, 2 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerFilter());

        ChannelFuture sync = bootstrap.bind(this.port).sync();
        // 关闭
        sync.channel().closeFuture().sync();
    }

    public void release() {
        if (bossEvenLoopGroup != null) {
            bossEvenLoopGroup.shutdownGracefully();
        }
        if (workEvenLoopGroup != null) {
            workEvenLoopGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws InterruptedException {


        NettyServer nettyServer = new NettyServer();
        nettyServer.release();


    }
}
