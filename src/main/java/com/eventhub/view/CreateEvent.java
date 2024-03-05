package main.java.com.eventhub.view;

import main.java.com.eventhub.controller.EventController;

public class CreateEvent extends AbstractView {
    private final EventController eventController;

    public CreateEvent(EventController eventController) {
        this.eventController = eventController;
    }

    @Override
    public void show() {
        scanner.nextLine();

        System.out.print("Digite o nome do evento: ");
        String name = scanner.nextLine();

        System.out.print("Digite a cidade do evento: ");
        String city = scanner.nextLine();

        System.out.print("Digite o endereço do evento: ");
        String addres = scanner.nextLine();

        System.out.print("Digite a descrição do evento: ");
        String description = scanner.nextLine();

        System.out.print("Digite a data e hora de início do evento no formato yyyy-MM-dd HH:mm: ");
        String startDate = scanner.nextLine();

        System.out.print("Digite a data e hora de término do evento no formato yyyy-MM-dd HH:mm: ");
        String endDate = scanner.nextLine();

        System.out.println("Categorias disponíveis: ");
        System.out.println("Festa");
        System.out.println("Esportivo");
        System.out.println("Show");
        System.out.println("Outro");

        System.out.print("Digite a categoria do evento: ");
        String category = scanner.nextLine();

        eventController.create(name, city, addres, category, startDate, endDate, description);

        System.out.println("\nEvento criado com sucesso!\n");
    }
}
