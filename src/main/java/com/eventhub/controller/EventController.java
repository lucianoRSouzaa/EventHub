package main.java.com.eventhub.controller;

import main.java.com.eventhub.model.Event;
import main.java.com.eventhub.model.EventCategory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Event getEventById(String eventId) {
        return Event.getEventById(events, eventId).getFirst();
    }

    public List<Event> getEventByName(String eventName) {
        return Event.getEventByName(events, eventName);
    }

    public void create(String name, String city, String address, String categoryString, String startDateTimeString, String endDateTimeString, String description){
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String category = categoryString.toUpperCase();

        EventCategory selectedCategory = EventCategory.valueOf(category);

        Event event = new Event(name, city, address, selectedCategory, startDateTime, endDateTime, description);
        event.saveEvents();
    }
    
}
