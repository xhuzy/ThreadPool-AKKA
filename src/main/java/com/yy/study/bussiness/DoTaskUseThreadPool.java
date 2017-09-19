package com.yy.study.bussiness;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * Created by zy48461 on 2017/9/19.
 */
@Component
public class DoTaskUseThreadPool {

    @Resource(name = "threadPool")
    private Executor executor;

    public void doTask(Runnable runnable) {
        executor.execute(runnable);
    }
}
