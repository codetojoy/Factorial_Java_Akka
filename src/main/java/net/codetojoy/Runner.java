package net.codetojoy;

import akka.actor.typed.ActorSystem;
import java.io.*;
import java.util.List;

import net.codetojoy.message.*;

public class Runner {
    public static void main(String[] args) {
        var rangeSize = 10;
        var max = 30;
        ActorSystem<BeginCommand> supervisor = ActorSystem.create(Supervisor.create(rangeSize, max), "supervisor");
        supervisor.tell(new BeginCommand("factorial"));

        try {
            promptForUserInput();
        } catch (Exception ignored) {
        } finally {
            supervisor.terminate();
        }
    }

    static void promptForUserInput() {
        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception ex) {
        }
    }
}
