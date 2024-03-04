package main.java.com.eventhub.controller;

import java.util.List;

import main.java.com.eventhub.model.Event;
import main.java.com.eventhub.model.User;
import main.java.com.eventhub.model.UserEvent;

public class UserEventController {

    List<Event> events;
    String userEmail;
    String userPassword;
    String eventName;
    
    public UserEventController(List<Event> events, String userEmail, String userPassword, String eventName){
        this.events = events;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.eventName = eventName;
    }

    public static boolean CheckUser(String userEmail, String userPassword) {
        User user = User.getUserByEmail(userEmail);
        return user.checkPassword(userPassword);
    }

    public void attendEvent() {
        boolean isUserChecked = UserEventController.CheckUser(userEmail, userPassword);
        if(isUserChecked){
            List<Event> events = Event.getEventByName(this.events, eventName);
            if(events.isEmpty()){
                System.out.println("\nNenhum envento encontrado com o nome: " + eventName + "\n");
            } else {
                Event event = events.getFirst();
                UserEvent userEvent = new UserEvent(userEmail, event.getId().toString());
                userEvent.saveUserEvent();
            }

        } else{

            System.out.println("\nEmail ou senha incorretos!\n");
        }

    }
}
