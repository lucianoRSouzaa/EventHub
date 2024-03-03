package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.model.Event;

public class ListEventsOccurring extends AbstractView {
    private final EventController eventController;

    public ListEventsOccurring(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        List<Event> events = eventController.getEventsOccurring();
        if (events.isEmpty()) {
            System.out.println("\nNenhum evento ocorrendo agora.\n");
        } else {
            System.out.println("\n" + events.size() + " eventos ocorrendo agora:");
            for (Event event : events) {
                System.out.println("_______________________________________________________________________________________________________\n");
                System.out.println("Nome: " + event.getName());
                System.out.println("Descrição: " + event.getDescription());
                System.out.println("Cidade: " + event.getCity());
                System.out.println("Endereço: " + event.getAddress());
                System.out.println("Categoria: " + event.getCategory());
                System.out.println("Começou em: " + event.getStartTime() + ", e irá terminar em: " + event.getEndTime() + "\n");
            }
        }
    }
}
