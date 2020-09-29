package net.codetojoy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Reporter extends AbstractBehavior<Reporter.Event> {
    public static Behavior<Reporter.Event> create() {
        return Behaviors.setup(Reporter::new);
    }

    private Reporter(ActorContext<Reporter.Event> context) {
        super(context);
    }

    public interface Event {}

    public static final class CalcEvent implements Event {
        final int a;
        final int b;
        final int c;
        final boolean isMatch;

        public CalcEvent(int a, int b, int c, boolean isMatch) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.isMatch = isMatch;
        }

        public String toString() {
            final String format = "a: %d b: %d c: %d isMatch: %b";
            return String.format(format, a, b, c, isMatch);
        }
    }

    @Override
    public Receive<Reporter.Event> createReceive() {
        return newReceiveBuilder().onMessage(CalcEvent.class, this::onCalcEvent).build();
    }

    private Behavior<Reporter.Event> onCalcEvent(CalcEvent calcEvent) {
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
