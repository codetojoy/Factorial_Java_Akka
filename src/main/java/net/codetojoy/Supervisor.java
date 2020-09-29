package net.codetojoy;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import java.util.*;

import net.codetojoy.util.Timer;

public class Supervisor extends AbstractBehavior<Supervisor.Command> {
    private static int rangeSize;
    private static int max;
    // this is probably not necessary:
    private static Map<String, ActorRef<Worker.Command>> workers = new HashMap<>();

    public interface Command {}

    public static final class BeginCommand implements Command {
        final String name;

        public BeginCommand(String name) {
            this.name = name;
        }
    }

    public static Behavior<Supervisor.Command> create(int rangeSize, int max) {
        Supervisor.rangeSize = rangeSize;
        Supervisor.max = max;
        return Behaviors.setup(Supervisor::new);
    }

    private Supervisor(ActorContext<Supervisor.Command> context) {
        super(context);
    }

    @Override
    public Receive<Supervisor.Command> createReceive() {
        return newReceiveBuilder().onMessage(BeginCommand.class, this::onBeginCommand).build();
    }

    private Behavior<Supervisor.Command> onBeginCommand(BeginCommand command) {
        try {
            var timer = new Timer();
            // create calculator
            ActorRef<Calculator.Command> calculator = getContext().spawn(Calculator.create(), "calculator");

            // create reporter
            ActorRef<Reporter.Event> reporter = getContext().spawn(Reporter.create(), "reporter");

            // create workers
            createWorkersPerRange(calculator, reporter);

            getContext().getLog().info("TRACER Supervisor {}", timer.getElapsed("onBeginCommand"));
        } catch (Exception ex) {
            getContext().getLog().error("TRACER Supervisor caught exception! ex: {}", ex.getMessage());
        }

        return this;
    }

    protected void createWorkersPerRange(ActorRef<Calculator.Command> calculator, ActorRef<Reporter.Event> reporter) {
        var isDone = false;
        var rangeIndex = 1;
        var ranges = new Ranges();

        while (! isDone) {
            var range =  ranges.getRange(rangeIndex, rangeSize, max);

            // getContext().getLog().info("TRACER Supervisor created worker {} {}", range.low, range.high);
            var workerName = "worker" + rangeIndex;
            ActorRef<Worker.Command> worker = getContext().spawn(Worker.create(), workerName);
            workers.put(workerName, worker);

            // assign range to Worker
            var processRangeCommand = new Worker.ProcessRangeCommand(range, calculator, reporter);
            worker.tell(processRangeCommand);

            if (range.high >= max) {
                isDone = true;
            }
            rangeIndex++;
        }
    }
}
