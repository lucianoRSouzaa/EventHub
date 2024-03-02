package main.java.com.eventhub.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void saveData(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados em arquivo: " + e.getMessage());
        }
    }
}

