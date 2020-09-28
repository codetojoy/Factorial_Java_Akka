package net.codetojoy;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

import net.codetojoy.message.*;

public class Worker extends AbstractBehavior<ProcessRangeCommand> {
    public static Behavior<ProcessRangeCommand> create() {
        return Behaviors.setup(Worker::new);
    }

    private Worker(ActorContext<ProcessRangeCommand> context) {
        super(context);
    }

    @Override
    public Receive<ProcessRangeCommand> createReceive() {
        return newReceiveBuilder().onMessage(ProcessRangeCommand.class, this::onProcessRangeCommand).build();
    }

    private Behavior<ProcessRangeCommand> onProcessRangeCommand(ProcessRangeCommand processRangeCommand) {

        var range = processRangeCommand.range;
        for (int a = range.low; a <= range.high; a++) {
            for (int b = range.low; b <= range.high; b++) {
                for (int c = range.low; c <= range.high; c++) {
                    var calcCommand = new CalcCommand(a, b, c, processRangeCommand.reporter);
                    processRangeCommand.calculator.tell(calcCommand);
                }
            }
        }

        return this;
    }
}
