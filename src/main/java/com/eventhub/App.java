package main.java.com.eventhub;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.view.ListEvents;
import main.java.com.eventhub.view.MainMenu;

public class App {    
    public static void main(String[] args) throws Exception {
        EventController eventController = new EventController();
        ListEvents listEvents = new ListEvents(eventController);

        while (true) {
            
            MainMenu.showMainMenu();
            int option = MainMenu.getUserOption();
            
            switch (option) {
                case 1:
                    // registerUser();
                    break;
                case 2:
                    // createEvent();
                    break;
                case 3:
                    // attendEvent();
                    break;
                case 4:
                    // listUsers();
                    break;
                case 5:
                    listEvents.show();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
