package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.controller.UserEventController;
import main.java.com.eventhub.model.Event;

public class ListUserEvents extends AbstractView {
    private final EventController eventController;

    public ListUserEvents(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        System.out.print("\nDigite seu email: ");
        String email = scanner.next();

        System.out.print("\nDigite sua senha: ");
        String password = scanner.next();
        
        boolean isUserChecked = UserEventController.CheckUser(email, password);

        

        if(isUserChecked){
            List<String> litIds = UserEventController.getEventIdsForUser(email);

            if (litIds.isEmpty()) {
                System.out.println("\nVocê não está participando de nenhum evento.\n");
            } else {
                for (String id : litIds) {
                    Event event = eventController.getEventById(id);
                    System.out.println("_______________________________________________________________________________________________________\n");
                    System.out.println("Nome: " + event.getName());
                    System.out.println("Descrição: " + event.getDescription());
                    System.out.println("Cidade: " + event.getCity());
                    System.out.println("Endereço: " + event.getAddress());
                    System.out.println("Categoria: " + event.getCategory());
                    System.out.println("Começa em: " + event.getStartTime() + " e termina em: " + event.getEndTime() + "\n");
                }
            }
        } else {
            System.out.println("\nEmail ou senha inválidos.\n");
        }
    }
}
