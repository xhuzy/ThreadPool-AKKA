package com.yy.study.bussiness;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * Created by zy48461 on 2017/9/19.
 */
public class Task implements Runnable {
    private int            num;
    private CountDownLatch countDownLatch;

    public Task(int num, CountDownLatch countDownLatch) {
        this.num = num;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            String message = MessageFormat.format("ThreadID:{0};ThreadName:{1},Num:{2}", Thread.currentThread().getId(), Thread.currentThread().getName(), this.num);
            System.out.println(message);
            Caculate.doCaculate();
            Thread.sleep(3);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
