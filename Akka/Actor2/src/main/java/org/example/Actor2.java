package org.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Actor2 extends AbstractActor {
    static public Props props() {
        return Props.create(Actor2.class, Actor2::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    System.out.println("Actor2 received message: " + msg);
                    getSender().tell("Hi from Actor2", getSelf());
                })
                .build();
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("ActorSystem2");
        ActorRef actor2 = system.actorOf(Actor2.props(), "actor2");
    }

}
