package main.java.com.eventhub.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.eventhub.util.FileManager;

public class UserEvent implements IUserEvent {
    private static final String USER_EVENTS_FILE = "users-events.data";

    private String userEmail;
    private String eventId;

    public UserEvent(String userEmail, String eventId) {
        this.userEmail = userEmail;
        this.eventId = eventId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getEventId() {
        return eventId;
    }

    private String serialize() {
        return userEmail + "," + eventId;
    }

    public static UserEvent deserialize(String line) {
        String[] parts = line.split(",");
        String userId = parts[0];
        String eventId = parts[1];
        return new UserEvent(userId, eventId);
    }

    public void saveUserEvent() {
        String userEventData = serialize();

        FileManager.saveData(USER_EVENTS_FILE, userEventData);
    }

    public static List<String> getEventIdsForUser(String userEmail) {
        List<String> eventIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_EVENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                UserEvent userEvent = UserEvent.deserialize(line);
                if (userEvent.getUserEmail().equals(userEmail)) {
                    eventIds.add(userEvent.getEventId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventIds;
    }

    public static void cancelUserEvent(String userEmail, String eventId) {
        try {
            File inputFile = new File(USER_EVENTS_FILE);
            File tempFile = new File("temp-users-events.data");
    
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String lineToRemove = userEmail + "," + eventId;
            String currentLine;
    
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
    
            writer.close();
            reader.close();
    
            if (!inputFile.delete()) {
                System.err.println("Erro ao deletar o arquivo original");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Erro ao renomear o arquivo temporário");
            }
        } catch (IOException e) {
            System.err.println("Erro ao cancelar a participação do usuário no evento: " + e.getMessage());
        }
    }
}
