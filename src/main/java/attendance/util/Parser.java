package attendance.util;

import attendance.constant.ErrorMessage;
import java.time.LocalTime;

public class Parser {

    public static LocalTime parseLocalTime(String input) {
        try {
            String[] splitInput = input.split(":");
            int hour = validateNumber(splitInput[0]);
            int minute = validateNumber(splitInput[1]);
            return LocalTime.of(hour, minute);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static int validateNumber(String input) {
        try {
            return Integer.parseInt(input.strip());
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}
