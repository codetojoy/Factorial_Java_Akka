package net.codetojoy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import akka.actor.typed.ActorRef;

import net.codetojoy.service.Services;

public class Calculator extends AbstractBehavior<Calculator.Command> {

    public static Behavior<Calculator.Command> create() {
        return Behaviors.setup(Calculator::new);
    }

    private Calculator(ActorContext<Calculator.Command> context) {
        super(context);
    }

    public interface Command {}

    public static final class CalcCommand implements Command {
        final int a;
        final int b;
        final int c;
        final ActorRef<Reporter.Event> replyTo;

        public CalcCommand(int a, int b, int c, ActorRef<Reporter.Event> replyTo) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.replyTo = replyTo;
        }

        public String toString() {
            final String format = "a: %d b: %d c: %d";
            return String.format(format, a, b, c);
        }
    }

    @Override
    public Receive<Calculator.Command> createReceive() {
        return newReceiveBuilder().onMessage(CalcCommand.class, this::onCalcCommand).build();
    }

    private Behavior<Calculator.Command> onCalcCommand(CalcCommand calcCommand) {
        int a = calcCommand.a;
        int b = calcCommand.b;
        int c = calcCommand.c;

        boolean isMatch = Services.getFactorialService().isEqual(a, b, c);

        if (isMatch) {
            getContext().getLog().info("TRACER Calculator match: {}", calcCommand.toString());
            boolean result = true;
            var calcEvent = new Reporter.CalcEvent(a, b, c, result);
            calcCommand.replyTo.tell(calcEvent);
        }

        return this;
    }
}
