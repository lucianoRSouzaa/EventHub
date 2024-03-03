package main.java.com.eventhub.view;

import java.util.Scanner;

public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static int Home() {
        System.out.println("Escolha uma opcao: ");
        System.out.println("1 - Registrar usuario");
        System.out.println("2 - Criar evento");
        System.out.println("3 - Participar de evento");
        System.out.println("4 - Listar usuarios");
        System.out.println("5 - Listar eventos");
        System.out.println("6 - Sair");
        System.out.print("\nSelecione: ");
        
        int option = scanner.nextInt();

        return option;
    }
}
