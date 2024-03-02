package main.java.com.eventhub.model;

public interface IUserEvent {
    String getUserEmail();
    String getEventId();

    void saveUserEvent();
}
