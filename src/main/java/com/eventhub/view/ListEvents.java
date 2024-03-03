package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.model.Event;

public class ListEvents extends AbstractView {
    private final EventController eventController;

    public ListEvents(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        List<Event> events = eventController.getEvents();
        if (events.isEmpty()) {
            System.out.println("Nenhum evento criado.");
        } else {
            System.out.println("Eventos criados:");
            for (Event event : events) {
                System.out.println("\n_______________________________________________________________________________________________________\n");
                System.out.println("Nome: " + event.getName());
                System.out.println("Descrição: " + event.getDescription());
                System.out.println("Cidade: " + event.getCity());
                System.out.println("Endereço: " + event.getAddress());
                System.out.println("Categoria: " + event.getCategory());
                System.out.println("Começa em: " + event.getStartTime() + " e termina em: " + event.getEndTime());
            }
        }
    }
}
