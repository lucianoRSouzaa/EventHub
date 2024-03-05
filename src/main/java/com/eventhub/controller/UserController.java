package main.java.com.eventhub.controller;

import java.util.List;

import main.java.com.eventhub.model.User;

public class UserController {

    public static void register(String name, String email, String city, String password){
        User user = new User(name, email, city, password);
        user.saveUser();
    }

    public static List<User> get(){
        return User.getAllUsers();
    }
}
