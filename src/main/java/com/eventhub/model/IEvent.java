package main.java.com.eventhub.model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEvent {
    UUID getId();
    
    String getName();
    void setName(String name);

    String getAddress();
    void setAddress(String address);

    EventCategory getCategory();
    void setCategory(EventCategory category);

    LocalDateTime getStartTime();
    void setStartTime(LocalDateTime startTime);

    LocalDateTime getEndTime();
    void setEndTime(LocalDateTime endTime);

    String getDescription();
    void setDescription(String description);

    String getCity();
    void setCity(String city);

    LocalDateTime getCreatedAt();

    String toString();

    void saveEvents();
}
