package com.tian.spring.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
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

    public NettyServer() throws InterruptedException {
        init();
    }


    private void init() throws InterruptedException {
        bossEvenLoopGroup = new EpollEventLoopGroup();
        workEvenLoopGroup = new EpollEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossEvenLoopGroup, workEvenLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerFilter())
                .option(ChannelOption.SO_RCVBUF, 2 * 1024)
                .option(ChannelOption.SO_SNDBUF, 2 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);
//                .option(ChannelOption.SO_BACKLOG,128)

        ChannelFuture sync = bootstrap.bind().sync();
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
}
