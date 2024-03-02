package test.java.com.eventhub.model;

import org.junit.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    public void should_GetUserByEmail_WithValidEmail() {
        
        User user = new User("Luciano", "luciano@example.com", "São Paulo", "123456");
        user.saveUser();
        
        User retrievedUser = User.getUserByEmail(user.getEmail());

        Assert.assertNotNull("Usuário não encontrado pelo email existente", retrievedUser);
        Assert.assertEquals(user.getName(), retrievedUser.getName());
        Assert.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assert.assertEquals(user.getCity(), retrievedUser.getCity());
        Assert.assertEquals(null, retrievedUser.getPassword());
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
