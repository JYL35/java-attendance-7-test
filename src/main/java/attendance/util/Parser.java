package attendance.util;

import attendance.constant.ErrorMessage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime parseLocalTime(String input) {
        try {
            return LocalTime.parse(input, formatter);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}
