package net.codetojoy;

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.testkit.typed.javadsl.TestProbe;
import akka.actor.typed.ActorRef;
import org.junit.ClassRule;
import org.junit.Test;

public class RunnerTest {

    @ClassRule
    public static final TestKitJunitResource testKit = new TestKitJunitResource();

    @Test
    public void testGreeterActorSendingOfGreeting() {
        /*
        String caseId = "5150";
        String payload = "abc,def";
        boolean isDone = false;

        TestProbe<Greeted> testProbe = testKit.createTestProbe();
        ActorRef<Greet> underTest = testKit.spawn(Greeter.create(), "greeter");
        underTest.tell(new Greet(caseId, payload, isDone, "Charles", testProbe.getRef()));
        testProbe.expectMessage(new Greeted(caseId, "Charles", underTest));
        */
    }
}
