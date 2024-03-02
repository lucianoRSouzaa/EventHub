package main.java.com.eventhub.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private static String DATA_FILE = "users.data";

    private String name;
    private String email;
    private String city;
    private String password;

    public User(String name, String email, String city, String password) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.password = encryptPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveUser() {
        User existingUser = getUserByEmail(email);
    
        if (existingUser != null) {
            System.out.println("Já existe um usuário com este email: " + email);
            return;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
            writer.write(name + "," + email + "," + city + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o usuário em arquivo: " + e.getMessage());
        }
    }

    public static User getUserByEmail(String userEmail) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[1].equals(userEmail)) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3]);
                    user.password = null;
                    return user;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de usuários: " + e.getMessage());
        }
        return null;
    }

    public String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkPassword(String password) {
        String inputPassword = encryptPassword(password);
        return inputPassword.equals(this.password);
    }
}
