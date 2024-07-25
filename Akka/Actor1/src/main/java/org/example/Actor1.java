package org.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Actor1 extends AbstractActor {
    static public Props props() {
        return Props.create(Actor1.class, Actor1::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    System.out.println("Actor1 received message: " + msg);
                })
                .build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ActorSystem1");
        ActorRef actor1 = system.actorOf(Actor1.props(), "actor1");

        ActorSelection actor2Selection = system.actorSelection("akka://ActorSystem2@127.0.0.1:2552/user/actor2");
        actor2Selection.tell("Hi from Actor1", actor1);
    }
}
