package com.yy.study.akkaTest;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.yy.study.akka.HelloWorld;
import com.yy.study.akka.Main;

/**
 * Created by zy48461 on 2017/9/20.
 */
public class MainClass {
    public static void main(String[] args) {
        //ActorSystem 相当于ActorManager，管理各种Acor调度、线程管理等
        ActorSystem system = ActorSystem.create("hello");

        //        //创建一个HelloWorld 类型的Actor，在Actor启动前，会调preStart（），此时会想Greeter发消息
        //        ActorRef actorRef = system.actorOf(Props.create(HelloActor.class));
        //        actorRef.tell(new Greeting("Hello"), ActorRef.noSender());
        //        for (int i = 0; i < 1000; i++) {
        //            actorRef.tell("" + i, ActorRef.noSender());
        //        }
        //        actorRef.tell(new Greeting("Hi"), ActorRef.noSender());
        //        for (int i = 1000; i < 2000; i++) {
        //            actorRef.tell("" + i, ActorRef.noSender());
        //        }
        //
        //        System.out.println("finish");
        //        try {
        //            Thread.sleep(1000);
        //        } catch (Exception ex) {
        //            ex.printStackTrace();
        //        }

                for (int i = 0; i < 30000000; i++) {
                    system.actorOf(Props.create(AkkaTask.class)).tell(i,ActorRef.noSender());
                }
                System.out.println("finish");
    }
}
