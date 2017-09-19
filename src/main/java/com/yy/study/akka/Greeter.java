package com.yy.study.akka;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public static enum Msg{
        GREET , DONE;
    }
    /**
     * 每个Actor必须实现OnReceive，当该Actor收到消息调用该方法
     */
    @Override
    public void onReceive(Object msg) {
        System.out.print("Greeter.msg");
        if(msg == Msg.GREET){
            System.out.println("Hello world");
            /**
             * 这里吐槽一下Akka对于发消息的设计，发送消息的设计竟然是：
             * receiver.tell(msg , sender）
             * 也许没理解akka设计的理念，但是正常人设计不应该是：
             *  sender.tell(msg ， receiver）
             *  汗……
             */
            getSender().tell(Msg.DONE, getSelf());
        }else{
            System.out.print("Greeter.msg");
            unhandled(msg);
        }

    }

}