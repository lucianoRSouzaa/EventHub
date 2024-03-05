package main.java.com.eventhub.view;

import java.util.List;

import main.java.com.eventhub.controller.UserController;
import main.java.com.eventhub.model.User;

public class ListUsers extends AbstractView {
    
    @Override
    public void show() {
        List<User> users = UserController.get();

        System.out.println("\n" + users.size() + " usuários encontrados:");
        for (User user : users) {
            System.out.println("_______________________________________________________________________________________________________\n");
            System.out.println("Nome: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Email: " + user.getCity() + '\n');
        }
    }
}
