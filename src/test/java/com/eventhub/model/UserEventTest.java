package test.java.com.eventhub.model;

import org.junit.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import main.java.com.eventhub.model.UserEvent;

public class UserEventTest {

    @Before
    public void setup() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users-events.data"))) {
            writer.print("");
        } catch (IOException e) {
            Assert.fail("Erro ao limpar o arquivo de teste: " + e.getMessage());
        }
    }

    @Test
    public void should_GetEventIdsForUserSuccessfully() {
        UserEvent userEvent1 = new UserEvent("user1@example.com", "event1");
        UserEvent userEvent2 = new UserEvent("user1@example.com", "event2");
        UserEvent userEvent3 = new UserEvent("user2@example.com", "event3");

        userEvent1.saveUserEvent();
        userEvent2.saveUserEvent();
        userEvent3.saveUserEvent();

        List<String> eventIdsForUser1 = UserEvent.getEventIdsForUser("user1@example.com");
        List<String> eventIdsForUser2 = UserEvent.getEventIdsForUser("user2@example.com");

        Assert.assertEquals(2, eventIdsForUser1.size());
        Assert.assertTrue(eventIdsForUser1.contains("event1"));
        Assert.assertTrue(eventIdsForUser1.contains("event2"));
        
        Assert.assertEquals(1, eventIdsForUser2.size());
        Assert.assertTrue(eventIdsForUser2.contains("event3"));
    }

    @Test
    public void should_CancelUserEventSuccessfully() {
        UserEvent userEvent1 = new UserEvent("user1@example.com", "event1");
        UserEvent userEvent2 = new UserEvent("user1@example.com", "event2");
        UserEvent userEvent3 = new UserEvent("user2@example.com", "event3");

        userEvent1.saveUserEvent();
        userEvent2.saveUserEvent();
        userEvent3.saveUserEvent();

        UserEvent.cancelUserEvent("user1@example.com", "event1");

        List<String> eventIdsForUser1AfterCancel = UserEvent.getEventIdsForUser("user1@example.com");
        List<String> eventIdsForUser2AfterCancel = UserEvent.getEventIdsForUser("user2@example.com");

        Assert.assertEquals(1, eventIdsForUser1AfterCancel.size());
        Assert.assertFalse(eventIdsForUser1AfterCancel.contains("event1"));
        Assert.assertTrue(eventIdsForUser1AfterCancel.contains("event2"));
        Assert.assertEquals(1, eventIdsForUser2AfterCancel.size());
        Assert.assertTrue(eventIdsForUser2AfterCancel.contains("event3"));
    }
}
