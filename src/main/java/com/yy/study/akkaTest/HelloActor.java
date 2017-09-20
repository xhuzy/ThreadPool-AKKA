package com.yy.study.akkaTest;

import akka.actor.UntypedActor;

import java.text.MessageFormat;

/**
 * Created by zy48461 on 2017/9/20.
 */
public class HelloActor extends UntypedActor {

    private String greeting;

    public void onReceive(Object o) throws Exception {

        long sleep=(long)(Math.random()*1000);
        Thread.sleep(sleep);
        if (o instanceof Greeting) {
            Greeting greet = (Greeting) o;
            this.greeting = greet.getGreet();
        } else {
            String message = MessageFormat.format("ThreadID:{0};ThreadName:{1}", Thread.currentThread().getId(), Thread.currentThread().getName());
            System.out.println(this.greeting + o + ":" + message+":sleep:"+sleep);
        }
    }
}
