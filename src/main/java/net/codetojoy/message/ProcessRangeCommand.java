package net.codetojoy.message;

import net.codetojoy.Range;

import akka.actor.typed.ActorRef;

public final class ProcessRangeCommand {
    public final Range range;
    public final ActorRef<CalcCommand> calculator;
    public final ActorRef<CalcEvent> reporter;

    public ProcessRangeCommand(Range range, ActorRef<CalcCommand> calculator, ActorRef<CalcEvent> reporter) {
        this.range = range;
        this.calculator = calculator;
        this.reporter = reporter;
    }
}
