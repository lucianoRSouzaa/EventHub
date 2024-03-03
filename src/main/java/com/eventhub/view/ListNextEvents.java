package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.model.Event;

public class ListNextEvents extends AbstractView {
    private final EventController eventController;

    public ListNextEvents(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        List<Event> events = eventController.getNextEvents();
        if (events.isEmpty()) {
            System.out.println("\nNenhum evento criado.\n");
        } else {
            System.out.println("\n" + events.size() + " eventos criados:");
            for (Event event : events) {
                System.out.println("_______________________________________________________________________________________________________\n");
                System.out.println("Nome: " + event.getName());
                System.out.println("Descrição: " + event.getDescription());
                System.out.println("Cidade: " + event.getCity());
                System.out.println("Endereço: " + event.getAddress());
                System.out.println("Categoria: " + event.getCategory());
                System.out.println("Começa em: " + event.getStartTime() + " e termina em: " + event.getEndTime() + "\n");
            }
        }
    }
}
