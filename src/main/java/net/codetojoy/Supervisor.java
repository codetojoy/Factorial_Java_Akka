package net.codetojoy;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import net.codetojoy.message.*;
import net.codetojoy.util.Timer;

public class Supervisor extends AbstractBehavior<BeginCommand> {
    private static Range range;

    public static Behavior<BeginCommand> create(Range range) {
        Supervisor.range = range;
        return Behaviors.setup(Supervisor::new);
    }

    private Supervisor(ActorContext<BeginCommand> context) {
        super(context);
    }

    @Override
    public Receive<BeginCommand> createReceive() {
        return newReceiveBuilder().onMessage(BeginCommand.class, this::onBeginCommand).build();
    }

    private Behavior<BeginCommand> onBeginCommand(BeginCommand command) {
        try {
            var timer = new Timer();
            // create calculator
            ActorRef<CalcCommand> calculator = getContext().spawn(Calculator.create(), "calculator");

            // create reporter
            ActorRef<CalcEvent> reporter = getContext().spawn(Reporter.create(), "reporter");

            // create workers
            ActorRef<ProcessRangeCommand> worker = getContext().spawn(Worker.create(), "workerN");

            // assign blocks to workers
            var processRangeCommand = new ProcessRangeCommand(Supervisor.range, calculator, reporter);
            worker.tell(processRangeCommand);

            getContext().getLog().info("TRACER Supervisor {}", timer.getElapsed("onBeginCommand"));
        } catch (Exception ex) {
            getContext().getLog().error("TRACER Supervisor caught exception! ex: {}", ex.getMessage());
        }

        return this;
    }
}
