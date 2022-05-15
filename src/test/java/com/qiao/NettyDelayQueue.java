package com.qiao;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class NettyDelayQueue {
    public static void main(String[] args) {
        Timer timer = new HashedWheelTimer();

        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order 1, will be executed in 5 seconds");
                timer.newTimeout(this, 5, TimeUnit.SECONDS);
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);

        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("order 2, will be executed in 10 seconds");
                timer.newTimeout(this, 10, TimeUnit.SECONDS);
            }
        };
        timer.newTimeout(task2, 10, TimeUnit.SECONDS);

        TimerTask task3 = timeout -> System.out.println("order 3, will be executed in 15 seconds");
        timer.newTimeout(task3, 15, TimeUnit.SECONDS);

    }
}
