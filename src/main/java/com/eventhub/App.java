package main.java.com.eventhub;

import main.java.com.eventhub.controller.EventController;
import main.java.com.eventhub.view.ListEventsOccurring;
import main.java.com.eventhub.view.ListNextEvents;
import main.java.com.eventhub.view.ListNextEventsOfCity;
import main.java.com.eventhub.view.ListOccurredEvents;
import main.java.com.eventhub.view.ListUserEvents;
import main.java.com.eventhub.view.MainMenu;
import main.java.com.eventhub.view.NextEventsOptions;
import main.java.com.eventhub.view.NextUserEventsOption;
import main.java.com.eventhub.view.RegisterUser;

public class App {    
    public static void main(String[] args) throws Exception {
        EventController eventController = new EventController();
        ListNextEvents listNextEvents = new ListNextEvents(eventController);
        ListOccurredEvents listOccurredEvents = new ListOccurredEvents(eventController);
        ListEventsOccurring listEventsOccurring = new ListEventsOccurring(eventController);
        NextEventsOptions nextEventsOption = new NextEventsOptions(eventController);
        ListNextEventsOfCity listNextEventsOfCity = new ListNextEventsOfCity(eventController);
        ListUserEvents listUserEvents = new ListUserEvents(eventController);
        NextUserEventsOption nextUserEventsOption = new NextUserEventsOption(eventController);
        RegisterUser registerUser = new RegisterUser();

        while (true) {
            
            MainMenu.showMainMenu();
            int option = MainMenu.getUserOption();
            
            switch (option) {
                case 1:
                    registerUser.show();
                    break;
                case 2:
                    // createEvent();
                    break;
                case 3:
                    // listUsers();
                    break;
                case 4:
                    listNextEvents.show();
                    nextEventsOption.show();

                    break;
                case 5:
                    listNextEventsOfCity.show();
                    nextEventsOption.show();

                    break;
                case 6:
                    listOccurredEvents.show();
                    break;
                case 7:
                    listEventsOccurring.show();
                    break;
                case 8:
                    listUserEvents.show();
                    nextUserEventsOption.show();

                    break;
                case 9:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
