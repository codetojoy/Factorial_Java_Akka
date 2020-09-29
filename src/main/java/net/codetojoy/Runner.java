package net.codetojoy;

import akka.actor.typed.ActorSystem;
import java.io.*;
import java.util.List;

import net.codetojoy.message.*;
import net.codetojoy.service.Services;

public class Runner {
    public static void main(String[] args) {
        var rangeSize = parseIntArg(args, 0);
        var max = parseIntArg(args, 1);
        Services.init(max);
        
        ActorSystem<BeginCommand> supervisor = ActorSystem.create(Supervisor.create(rangeSize, max), "supervisor");
        supervisor.tell(new BeginCommand("factorial"));

        try {
            promptForUserInput();
        } catch (Exception ignored) {
        } finally {
            supervisor.terminate();
        }
    }

    static int parseIntArg(String[] args, int index) {
        int result = -1;
        boolean ok = false;

        try {
            result = Integer.parseInt(args[index]);
            ok = true;
        } catch (Exception ex) {
        }

        if (! ok) {
            System.err.println("error on arguments");
            System.exit(-1);
        }

        return result;
    }

    static void promptForUserInput() {
        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception ex) {
        }
    }
}
