package com.qiao;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

import java.nio.channels.Selector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        Selector.open();

        int nThread = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(nThread);
        System.out.println(NettyRuntime.availableProcessors());

        System.out.println(SystemPropertyUtil.getInt("io.netty.eventLoopThreads", 1));
    }
}
