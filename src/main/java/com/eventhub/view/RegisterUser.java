package main.java.com.eventhub.view;

import main.java.com.eventhub.controller.UserController;

public class RegisterUser extends AbstractView {

    @Override
    public void show() {
        System.out.print("Digite seu nome: ");
        String name = scanner.next();

        System.out.print("Digite o nome de sua cidade: ");
        String city = scanner.next();

        System.out.print("Digite seu email: ");
        String email = scanner.next();

        System.out.print("Digite sua senha: ");
        String password = scanner.next();

        UserController.register(name, email, city, password);
    }
}
