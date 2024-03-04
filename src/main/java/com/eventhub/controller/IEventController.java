package main.java.com.eventhub.controller;

import java.util.List;

import main.java.com.eventhub.model.Event;

public interface IEventController {
    List<Event> getNextEvents();
    List<Event> getOccurredEvents();
    List<Event> getEventsOccurring();
    List<Event> getNextEventsOfUserCity(String city);
}
