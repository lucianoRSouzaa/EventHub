package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.controller.UserEventController;
import main.java.com.eventhub.model.Event;

public class NextUserEventsOption extends AbstractView {
    private final EventController eventController;

    public NextUserEventsOption(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        System.out.println("O que você deseja fazer agora? ");
        System.out.println("1 - Cancelar participação em um evento");
        System.out.println("2 - Voltar");
        System.out.print("\nSelecione: ");

        int option = scanner.nextInt();

        if(option == 1){
            System.out.print("\nDigite o nome do evento: ");
            String eventName = scanner.next();

            System.out.print("\nDigite seu email: ");
            String email = scanner.next();

            System.out.print("\nDigite sua senha: ");
            String password = scanner.next();

            List<Event> events = eventController.getEventByName(eventName);

            UserEventController userEventController = new UserEventController(events, email, password, eventName);
            userEventController.cancelUserEvent();
        }

        return;
    }
}

