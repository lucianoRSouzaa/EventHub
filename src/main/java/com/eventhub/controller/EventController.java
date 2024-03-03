package main.java.com.eventhub.controller;

import main.java.com.eventhub.model.Event;

import java.util.List;

public class EventController {
    public List<Event> getEvents() {
        return Event.readEventsFromFile();
    }
}
