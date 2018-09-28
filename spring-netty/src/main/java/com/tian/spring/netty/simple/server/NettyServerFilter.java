package com.tian.spring.netty.simple.server;

import com.tian.spring.netty.proxy.JDKProxy;
import com.tian.spring.netty.service.HelloService;
import com.tian.spring.netty.service.HelloServiceImpl;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author tianbeiping
 * @Title: NettyServerFilter
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:04
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        // 以("\n")为结尾分割的 解码器
//        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 解码和编码，应和客户端一致
        ph.addLast("encoder", new StringEncoder());
        ph.addLast("decoder", new StringDecoder());
        // 服务端业务逻辑
        HelloService helloService = JDKProxy.proxyClass(HelloService.class, new HelloServiceImpl());
        ph.addLast("handler", new NettyServerHandler(helloService));
    }
}