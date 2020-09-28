package net.codetojoy.message;

import akka.actor.typed.ActorRef;

public final class CalcCommand {
    public final int a;
    public final int b;
    public final int c;
    public final ActorRef<CalcEvent> replyTo;

    public CalcCommand(int a, int b, int c, ActorRef<CalcEvent> replyTo) {
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
