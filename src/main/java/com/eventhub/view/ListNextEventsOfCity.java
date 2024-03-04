package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.controller.UserEventController;
import main.java.com.eventhub.model.Event;
import main.java.com.eventhub.model.User;

public class ListNextEventsOfCity extends AbstractView {
    private final EventController eventController;

    public ListNextEventsOfCity(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        List<Event> events = eventController.getNextEvents();
        System.out.print("\nDigite seu email: ");
        String email = scanner.next();

        System.out.print("\nDigite sua senha: ");
        String password = scanner.next();
        
        boolean isUserChecked = UserEventController.CheckUser(email, password);

        
        if (events.isEmpty()) {
            System.out.println("\nNenhum evento criado.\n");
        } else {
            if(isUserChecked){
                User user = User.getUserByEmail(email);
                List<Event> eventsOfUserCity = eventController.getNextEventsOfUserCity(user.getCity());

                if (eventsOfUserCity.isEmpty()) {
                    System.out.println("\nNenhum evento criado na sua cidade.\n");
                } else {
                    System.out.println("\n" + eventsOfUserCity.size() + " eventos criados na sua cidade:");
                    for (Event event : eventsOfUserCity) {
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
}
