package test.java.com.eventhub.model;

import org.junit.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import main.java.com.eventhub.model.Event;
import main.java.com.eventhub.model.EventCategory;

public class EventTest {

    private static final String DATA_FILE = "events.data";

    @Before
    public void setup() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.print("");
        } catch (IOException e) {
            Assert.fail("Erro ao limpar o arquivo de teste: " + e.getMessage());
        }
    }

    @Test
    public void should_SaveEventSuccessfully() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        Event event = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, dateTime, dateTime, "Live music performance");

        event.saveEvents();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line1 = reader.readLine();

            String expectedLine = event.toString();
            Assert.assertEquals(expectedLine, line1);
        } catch (IOException e) {
            Assert.fail("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    @Test
    public void should_SaveMultipleEventsSuccessfully() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, dateTime, dateTime, "Live music performance");

        LocalDateTime now = LocalDateTime.now();
        Event event2 = new Event("Party", "São Paulo", "456 Oak St", EventCategory.FESTA, now.minusMinutes(30), now.minusMinutes(30), "Night party");

        event1.saveEvents();
        event2.saveEvents();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            String expectedLine1 = event1.toString();
            String expectedLine2 = event2.toString();

            Assert.assertEquals(expectedLine1, line1);
            Assert.assertEquals(expectedLine2, line2);

        } catch (IOException e) {
            Assert.fail("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    @Test
    public void should_ReadEventsFromFile() {
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, now.plusDays(1), now.plusDays(1), "Live music performance");
        event1.saveEvents();

        Event event2 = new Event("Party", "São Paulo", "456 Oak St", EventCategory.FESTA, now.minusMinutes(30), now.minusMinutes(30), "Night party");
        event2.saveEvents();

        Event event3 = new Event("Exhibition", "São Paulo", "101 Pine St", EventCategory.OUTRO, now.minusDays(1), now.minusDays(1), "Art exhibition");
        event3.saveEvents();

        List<Event> events = Event.readEventsFromFile();

        Assert.assertEquals(3, events.size());

        Event firstEvent = events.get(0);
        Event secondEvent = events.get(1);
        Event thirdEvent = events.get(2);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            String line3 = reader.readLine();

            String expectedLine1 = firstEvent.toString();
            String expectedLine2 = secondEvent.toString();
            String expectedLine3 = thirdEvent.toString();

            Assert.assertEquals(expectedLine1, line1);
            Assert.assertEquals(expectedLine2, line2);
            Assert.assertEquals(expectedLine3, line3);

        } catch (IOException e) {
            Assert.fail("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    @Test
    public void should_ListUpcomingEvents() {
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, now.plusDays(1), now.plusDays(1), "Live music performance");
        Event event2 = new Event("Party", "São Paulo", "456 Oak St", EventCategory.FESTA, now.minusMinutes(30), now.minusMinutes(30), "Night party");
        Event event3 = new Event("Exhibition", "São Paulo", "101 Pine St", EventCategory.OUTRO, now.minusDays(1), now.minusDays(1), "Art exhibition");
        Event event4 = new Event("Workshop", "São Paulo", "202 Maple St", EventCategory.FESTA, now.plusDays(5), now.plusDays(5), "Training workshop");

        event1.saveEvents();
        event2.saveEvents();
        event3.saveEvents();
        event4.saveEvents();

        List<Event> allEvents = Event.readEventsFromFile();

        List<Event> upcomingEvents = Event.listUpcomingEvents(allEvents);

        System.out.println("Upcoming events:");
        for (Event event : upcomingEvents) {
            System.out.println(event);
        }

        Assert.assertEquals(2, upcomingEvents.size());

        Assert.assertEquals(event1, upcomingEvents.get(0));
        Assert.assertEquals(event4, upcomingEvents.get(1));
    }

    @Test
    public void should_ListOngoingEvents() {
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, now.minusMinutes(30), now.plusMinutes(60), "Live music performance");
        Event event2 = new Event("Party", "São Paulo", "456 Oak St", EventCategory.FESTA, now.minusMinutes(30), now.plusMinutes(30), "Night party");
        Event event3 = new Event("Exhibition", "São Paulo", "101 Pine St", EventCategory.OUTRO, now.minusHours(2), now.minusHours(1), "Art exhibition");
        Event event4 = new Event("Workshop", "São Paulo", "202 Maple St", EventCategory.FESTA, now.plusMinutes(30), now.plusHours(2), "Training workshop");

        event1.saveEvents();
        event2.saveEvents();
        event3.saveEvents();
        event4.saveEvents();

        List<Event> allEvents = Event.readEventsFromFile();

        List<Event> ongoingEvents = Event.listOngoingEvents(allEvents);

        Assert.assertEquals(2, ongoingEvents.size());
        Assert.assertTrue(ongoingEvents.contains(event1));
        Assert.assertTrue(ongoingEvents.contains(event2));
    }

    @Test
    public void should_ListPastEvents() {
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, now.minusMinutes(90), now.plusMinutes(30), "Live music performance");
        Event event2 = new Event("Party", "São Paulo", "456 Oak St", EventCategory.FESTA, now.minusHours(2), now.minusMinutes(30), "Night party");
        Event event3 = new Event("Exhibition", "São Paulo", "101 Pine St", EventCategory.OUTRO, now.minusDays(1), now.minusHours(23), "Art exhibition");
        Event event4 = new Event("Workshop", "São Paulo", "202 Maple St", EventCategory.FESTA, now.plusMinutes(30), now.plusMinutes(90), "Training workshop");

        event1.saveEvents();
        event2.saveEvents();
        event3.saveEvents();
        event4.saveEvents();

        List<Event> allEvents = Event.readEventsFromFile();
        
        List<Event> pastEvents = Event.listPastEvents(allEvents);

        Assert.assertEquals(2, pastEvents.size());
        Assert.assertTrue(pastEvents.contains(event2));
        Assert.assertTrue(pastEvents.contains(event3));
    }

    @Test
    public void should_ListEventsByCity() {
        Event event1 = new Event("Concert", "São Paulo", "123 Main St", EventCategory.SHOW, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(2), "Live music performance");
        Event event2 = new Event("Party", "Rio de Janeiro", "456 Oak St", EventCategory.FESTA, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(3), "Night party");
        Event event3 = new Event("Exhibition", "Curitiba", "101 Pine St", EventCategory.OUTRO, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(4), "Art exhibition");
        Event event4 = new Event("Workshop", "são paulo", "202 Maple St", EventCategory.FESTA, LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(4).plusHours(5), "Training workshop");

        List<Event> events = List.of(event1, event2, event3, event4);
        
        List<Event> eventsInCity = Event.listEventsByCity(events, "São Paulo");

        Assert.assertEquals(2, eventsInCity.size());
        Assert.assertTrue(eventsInCity.contains(event1));
        Assert.assertTrue(eventsInCity.contains(event4));
    }
}
