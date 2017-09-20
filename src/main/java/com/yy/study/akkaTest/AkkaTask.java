package com.yy.study.akkaTest;

import akka.actor.UntypedActor;
import com.yy.study.bussiness.Caculate;

import java.text.MessageFormat;

/**
 * Created by zy48461 on 2017/9/20.
 */
public class AkkaTask extends UntypedActor {

    public void onReceive(Object o) throws Exception {
        Integer num = (Integer) o;
        String message = MessageFormat.format("ThreadID:{0};ThreadName:{1},Num:{2}", Thread.currentThread().getId(), Thread.currentThread().getName(), num);
        System.out.println(message);
        Caculate.doCaculate();
        long sleep=(long)(Math.random()*100);
        Thread.sleep(sleep);
    }
}
