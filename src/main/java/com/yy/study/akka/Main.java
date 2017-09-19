package com.yy.study.akka;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Main {

    public static void main(String[] args) {
        //ActorSystem 相当于ActorManager，管理各种Acor调度、线程管理等
        ActorSystem system = ActorSystem.create("hello");
        //创建一个HelloWorld 类型的Actor，在Actor启动前，会调preStart（），此时会想Greeter发消息
        ActorRef actor = system.actorOf(Props.create(HelloWorld.class));
        //添加结束终结Actor，当ActorSystem调Stop时，会向每个Actor发送Terminated消息    
        system.actorOf(Props.create(Terminator.class, actor), "terminator");

    }

    public static class Terminator extends UntypedActor {

        private final LoggingAdapter log      = Logging.getLogger(getContext().system(), this);
        private ActorRef             actorRef = null;

        public Terminator(ActorRef ref) {
            System.out.println("Terminator Init !!!");
            actorRef = ref;
            getContext().watch(actorRef);
        }

        @Override
        public void onReceive(Object msg) {
            if (msg instanceof Terminated) {
                log.info("{} has terminated, shutting down system", actorRef.path());
                getContext().system().terminate();
            } else {
                unhandled(msg);
            }

        }

    }

}