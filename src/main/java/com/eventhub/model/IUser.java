package main.java.com.eventhub.model;

public interface IUser {

    String getName();
    void setName(String name);

    String getEmail();
    void setEmail(String email);
    
    String getCity();
    void setCity(String city);

    String getPassword();
    void setPassword(String password);

    void saveUser();
    
    boolean checkPassword(String password);
    String encryptPassword(String password);

    String toString();

}
