package main.java.com.eventhub.view;

public class MainMenu extends AbstractView {

    @Override
    public void show() {
        System.out.println("Escolha uma opcao: ");
        System.out.println("1 - Registrar usuario");
        System.out.println("2 - Criar evento");
        System.out.println("3 - Listar usuarios");
        System.out.println("4 - Listar próximos eventos");
        System.out.println("5 - Listar próximos eventos da minha cidade");
        System.out.println("6 - Listar eventos que ja aconteceram");
        System.out.println("7 - Listar eventos que estão ocorrendo agora");
        System.out.println("8 - Listar eventos em que estou inscrito");
        System.out.println("9 - Sair");
        System.out.print("\nSelecione: ");
    }

    public static int getUserOption() {
        return scanner.nextInt();
    }

    public static void showMainMenu() {
        new MainMenu().show();
    }
}
