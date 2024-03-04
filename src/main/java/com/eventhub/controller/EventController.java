package main.java.com.eventhub.controller;

import main.java.com.eventhub.model.Event;

import java.util.List;

public class EventController implements IEventController {
    List<Event> events = Event.readEventsFromFile();

    public List<Event> getNextEvents() {
        return Event.listUpcomingEvents(events);
    }

    public List<Event> getOccurredEvents() {
        return Event.listPastEvents(events);
    }

    public List<Event> getEventsOccurring() {
        return Event.listOngoingEvents(events);
    }

    public List<Event> getNextEventsOfUserCity(String city) {
        List<Event> nextEvents = getNextEvents();
        return Event.listEventsByCity(nextEvents, city);
    }
}
