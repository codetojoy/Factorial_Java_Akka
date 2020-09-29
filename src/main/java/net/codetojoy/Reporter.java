package net.codetojoy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import net.codetojoy.message.*;

public class Reporter extends AbstractBehavior<CalcEvent> {
    public static Behavior<CalcEvent> create() {
        return Behaviors.setup(Reporter::new);
    }

    private Reporter(ActorContext<CalcEvent> context) {
        super(context);
    }

    @Override
    public Receive<CalcEvent> createReceive() {
        return newReceiveBuilder().onMessage(CalcEvent.class, this::onCalcEvent).build();
    }

    private Behavior<CalcEvent> onCalcEvent(CalcEvent calcEvent) {
        if (calcEvent.isMatch) {
            var a = calcEvent.a;
            var b = calcEvent.b;
            var c = calcEvent.c;
            getContext().getLog().info("TRACER Reporter MATCH {}! = {}! x {}!", a, b, c);
        } else {
            getContext().getLog().error("TRACER Reporter INTERNAL ERROR received: {}", calcEvent.toString());
        }

        return this;
    }
}
