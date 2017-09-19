package com.yy.study.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart(){
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class));
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }


    @Override
    public void onReceive(Object msg) {

        if(msg == Greeter.Msg.DONE){
            getContext().stop(getSelf());
        }else{
            System.out.print(msg);
            unhandled(msg);
        }

    }


}
