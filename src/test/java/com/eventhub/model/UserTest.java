package test.java.com.eventhub.model;

import org.junit.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import main.java.com.eventhub.model.User;

public class UserTest {

    @Before
    public void setup() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.data"))) {
            writer.print("");
        } catch (IOException e) {
            Assert.fail("Erro ao limpar o arquivo de teste: " + e.getMessage());
        }
    }


    @Test
    public void should_SaveUserSuccessfully() {
        User user = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        
        user.saveUser();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.data"))) {
            String line = reader.readLine();
            Assert.assertEquals(user.getName() + "," + user.getEmail() +  "," + user.getCity() + "," + user.getPassword(), line);
        } catch (IOException e) {
            Assert.fail("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    @Test
    public void should_SaveMultipleUsersSuccessfully() {
        User user1 = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        User user2 = new User("Maria", "maria@example.com", "Rio de Janeiro", "654321");
        
        user1.saveUser();
        user2.saveUser();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.data"))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            Assert.assertEquals(user1.getName() + "," + user1.getEmail() +  "," + user1.getCity() + "," + user1.getPassword(), line1);
            Assert.assertEquals(user2.getName() + "," + user2.getEmail() +  "," + user2.getCity() + "," + user2.getPassword(), line2);
        } catch (IOException e) {
            Assert.fail("Erro ao ler o arquivo users.data: " + e.getMessage());
        }
    }

    @Test
    public void should_ListAllUsersSuccessfully() {
        User user1 = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        User user2 = new User("Maria", "maria@example.com", "Rio de Janeiro", "654321");

        user1.saveUser();
        user2.saveUser();

        List<User> userList = User.getAllUsers();
        Assert.assertEquals(2, userList.size());

        Assert.assertEquals("Luciano", userList.get(0).getName());
        Assert.assertEquals("luciano@example.com", userList.get(0).getEmail());
        Assert.assertEquals("São Paulo", userList.get(0).getCity());
        Assert.assertEquals(null, userList.get(0).getPassword());

        Assert.assertEquals("Maria", userList.get(1).getName());
        Assert.assertEquals("maria@example.com", userList.get(1).getEmail());
        Assert.assertEquals("Rio de Janeiro", userList.get(1).getCity());
        Assert.assertEquals(null, userList.get(1).getPassword());
    }

    @Test
    public void should_GetUserByEmail_WithValidEmail() {
        
        User user = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        user.saveUser();
        
        User retrievedUser = User.getUserByEmail(user.getEmail());

        Assert.assertNotNull("Usuário não encontrado pelo email existente", retrievedUser);
        Assert.assertEquals(user.getName(), retrievedUser.getName());
        Assert.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assert.assertEquals(user.getCity(), retrievedUser.getCity());
    }
    
    @Test
    public void should_ReturnNull_ForInvalidEmail() {
        User retrievedUser = User.getUserByEmail("naoexiste@example.com");
        Assert.assertNull("O método não retornou null para um email que não existe", retrievedUser);
    }

    @Test
    public void should_CheckPassword_Correctly() {
        User user = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        
        Assert.assertTrue(user.checkPassword("123456"));
        
        Assert.assertFalse(user.checkPassword("passwordincorrect"));
    }
}
