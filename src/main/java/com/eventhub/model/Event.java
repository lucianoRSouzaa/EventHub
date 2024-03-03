package main.java.com.eventhub.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import main.java.com.eventhub.util.FileManager;
import main.java.com.eventhub.util.StringToLocalDateTime;


public class Event implements IEvent {
    private static String DATA_FILE = "events.data";

    private UUID id;
    private String name;
    private String city;
    private String address;
    private EventCategory category;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;
    private LocalDateTime createdAt;

    public Event(String name, String city, String address, EventCategory category, LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.city = city;
        this.address = address;
        this.category = category;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    private Event(UUID id, String name, String city, String address, EventCategory category, LocalDateTime startDateTime, LocalDateTime endDateTime, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.category = category;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.createdAt = createdAt;
    }

    public void saveEvents() {
        String eventData = this.toString();
    
        FileManager.saveData(DATA_FILE, eventData);
    }

    public static List<Event> readEventsFromFile() {
        List<Event> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] eventData = line.split(",");
                UUID id = UUID.fromString(eventData[0]);
                EventCategory category = EventCategory.valueOf(eventData[4].toUpperCase());
                Event event = new Event(id, eventData[1], eventData[2], eventData[3], category, StringToLocalDateTime.parse(eventData[5]), StringToLocalDateTime.parse(eventData[6]) , eventData[7], StringToLocalDateTime.parse(eventData[8]));
                
                events.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }

    public static List<Event> listPastEvents(List<Event> events) {
        LocalDateTime now = LocalDateTime.now();
        
        List<Event> pastEvents = new ArrayList<>();
        for (Event event : events) {
            LocalDateTime endTime = event.getEndTime();
            
            if (endTime.isBefore(now)) {
                pastEvents.add(event);
            }
        }
        
        return pastEvents;
    }

    public static List<Event> listUpcomingEvents(List<Event> events) {
        LocalDateTime now = LocalDateTime.now();
        
        List<Event> upcomingEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getStartTime().isAfter(now)) {
                upcomingEvents.add(event);
            }
        }
        
        upcomingEvents.sort(Comparator.comparing(Event::getStartTime));

        return upcomingEvents;
    }

    public static List<Event> listOngoingEvents(List<Event> events) {
        LocalDateTime now = LocalDateTime.now();
        
        List<Event> ongoingEvents = new ArrayList<>();
        for (Event event : events) {
            LocalDateTime startTime = event.getStartTime();
            LocalDateTime endTime = event.getEndTime();
            
            if (startTime.isBefore(now) && endTime.isAfter(now)) {
                ongoingEvents.add(event);
            }
        }
        
        return ongoingEvents;
    }

    public static List<Event> listEventsByCity(List<Event> events, String city) {
        List<Event> eventsByCity = new ArrayList<>();
        for (Event event : events) {
            if (event.getCity().equalsIgnoreCase(city)) {
                eventsByCity.add(event);
            }
        }
        return eventsByCity;
    }

    public static List<Event> getEventByName(List<Event> events, String name) {
        List<Event> eventsByName = new ArrayList<>();
        
        try{
            for (Event event : events) {
                if (event.getName().equalsIgnoreCase(name)) {
                    eventsByName.add(event);
                }
            }
        } catch(Exception e) {
            System.out.println("Nenhum evento encontrado com o nome " + name);
        }
        
        return eventsByName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return startDateTime;
    }

    public void setStartTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndTime() {
        return endDateTime;
    }

    public void setEndTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", id, name, city, address, category.name().toLowerCase(), startDateTime.toString(), endDateTime.toString(), description, createdAt.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(address, event.address) &&
                category == event.category &&
                Objects.equals(startDateTime, event.startDateTime) &&
                Objects.equals(endDateTime, event.endDateTime) &&
                Objects.equals(description, event.description) &&
                Objects.equals(city, event.city) &&
                Objects.equals(createdAt, event.createdAt);
    }
    
}
