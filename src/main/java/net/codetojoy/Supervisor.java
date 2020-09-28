package net.codetojoy;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.*;

import net.codetojoy.message.*;
import net.codetojoy.util.Timer;

public class Supervisor extends AbstractBehavior<BeginCommand> {
    private static int rangeSize;
    private static int max;
    // this is probably not necessary:
    private static Map<String, ActorRef<ProcessRangeCommand>> workers = new HashMap<>();

    public static Behavior<BeginCommand> create(int rangeSize, int max) {
        Supervisor.rangeSize = rangeSize;
        Supervisor.max = max;
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
            createWorkersPerRange(calculator, reporter);

            getContext().getLog().info("TRACER Supervisor {}", timer.getElapsed("onBeginCommand"));
        } catch (Exception ex) {
            getContext().getLog().error("TRACER Supervisor caught exception! ex: {}", ex.getMessage());
        }

        return this;
    }

    protected void createWorkersPerRange(ActorRef<CalcCommand> calculator, ActorRef<CalcEvent> reporter) {
        var isDone = false;
        var rangeIndex = 1;
        var ranges = new Ranges();

        while (! isDone) {
            var range =  ranges.getRange(rangeIndex, rangeSize, max);

            // getContext().getLog().info("TRACER Supervisor created worker {} {}", range.low, range.high);
            var workerName = "worker" + rangeIndex;
            ActorRef<ProcessRangeCommand> worker = getContext().spawn(Worker.create(), workerName);
            workers.put(workerName, worker);

            // assign range to Worker
            var processRangeCommand = new ProcessRangeCommand(range, calculator, reporter);
            worker.tell(processRangeCommand);

            if (range.high >= max) {
                isDone = true;
            }
            rangeIndex++;
        }
    }
}
