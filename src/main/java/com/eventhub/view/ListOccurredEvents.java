package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.model.Event;

public class ListOccurredEvents extends AbstractView {
    private final EventController eventController;

    public ListOccurredEvents(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        List<Event> events = eventController.getOccurredEvents();
        if (events.isEmpty()) {
            System.out.println("\nNenhum evento ocorrido ainda.\n");
        } else {
            System.out.println("\n" + events.size() + " eventos já ocorridos:");
            for (Event event : events) {
                System.out.println("_______________________________________________________________________________________________________\n");
                System.out.println("Nome: " + event.getName());
                System.out.println("Descrição: " + event.getDescription());
                System.out.println("Cidade: " + event.getCity());
                System.out.println("Endereço: " + event.getAddress());
                System.out.println("Categoria: " + event.getCategory());
                System.out.println("Ocorrido em: " + event.getStartTime() + ", terminado em: " + event.getEndTime() + "\n");
            }
        }
    }
}
