package net.codetojoy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import net.codetojoy.message.*;

public class Calculator extends AbstractBehavior<CalcCommand> {

    public static Behavior<CalcCommand> create() {
        return Behaviors.setup(Calculator::new);
    }

    private Calculator(ActorContext<CalcCommand> context) {
        super(context);
    }

    @Override
    public Receive<CalcCommand> createReceive() {
        return newReceiveBuilder().onMessage(CalcCommand.class, this::onCalcCommand).build();
    }

    private Behavior<CalcCommand> onCalcCommand(CalcCommand calcCommand) {
        int a = calcCommand.a;
        int b = calcCommand.b;
        int c = calcCommand.c;

        if ((a == b) && (b == c)) {
            // getContext().getLog().info("TRACER Calculator match: {}", calcCommand.toString());
            boolean result = true;
            var calcEvent = new CalcEvent(a, b, c, result);
            calcCommand.replyTo.tell(calcEvent);
        }

        return this;
    }
}
