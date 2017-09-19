package com.yy.study.main;

import com.yy.study.bussiness.DoTaskUseThreadPool;
import com.yy.study.bussiness.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zy48461 on 2017/9/19.
 */
public class MainClass {
    private  static int  totalTaskNum=30000;

    public static void main(String[] args) {
        try {
            doTaskbyThreadPool();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void doTaskbyThreadPool() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(totalTaskNum);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:*/threadpool-akka-springBean.xml");
        DoTaskUseThreadPool doTaskUseThreadPool = (DoTaskUseThreadPool) applicationContext.getBean("doTaskUseThreadPool");
        for (int i = 0; i < totalTaskNum; i++) {
            doTaskUseThreadPool.doTask(new Task(i,countDownLatch));
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.println("共计耗时:"+stopWatch.getTotalTimeSeconds());
    }
}
