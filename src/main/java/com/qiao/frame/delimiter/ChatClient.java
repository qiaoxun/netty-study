package com.qiao.frame.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8888;
        new ChatClient(host, port).run();
    }

    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());

            Channel channel = bootstrap.connect().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = br.readLine();
                if (null != line && line.equals("exit"))
                    break;
                channel.writeAndFlush(line + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }

    }
}
