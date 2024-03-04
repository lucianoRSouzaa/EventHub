package main.java.com.eventhub.controller;

import main.java.com.eventhub.model.User;

public class UserController {

    public static void register(String name, String email, String city, String password){
        User user = new User(name, email, city, password);
        user.saveUser();
    }
}
