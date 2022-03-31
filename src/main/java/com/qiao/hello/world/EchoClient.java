package com.qiao.hello.world;

import com.qiao.firstapplication.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // Creates Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            // Specifies EventLoopGroup to handle client events, NIO implementation is needed
            bootstrap.group(group)
                    // Channel type is the one for NIO transport
                    .channel(NioSocketChannel.class)
                    // Sets the server's InetSocket-Address
                    .remoteAddress(new InetSocketAddress(host, port))
                    // Adds an EchoClient-Handler to the pipeline when a channel is created
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // Connects to the remote peer, waits until the connection completes
            ChannelFuture cf = bootstrap.connect().sync();
            // Blocks until the Channel closes
            cf.channel().closeFuture().sync();
        } finally {
            // Shuts down the thread pools and the release of all resources
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8888;
        new com.qiao.firstapplication.EchoClient(host, port).start();
    }

}
