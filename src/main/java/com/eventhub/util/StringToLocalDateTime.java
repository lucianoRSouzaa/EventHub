package main.java.com.eventhub.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime {
     public static LocalDateTime parse(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);

        return localDateTime;
    }
}
