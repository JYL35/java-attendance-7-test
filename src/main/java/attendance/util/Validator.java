package attendance.util;

import attendance.constant.ErrorMessage;
import java.util.List;
import java.util.Set;

public class Validator {
    private static final List<String> OPTIONS = List.of("1", "2", "3", "4", "Q");

    public static void validateOption(String input) {
        validateEmpty(input);
        validateContains(input);
    }

    public static void validateNickName(String input, Set<String> nicknames) {
        validateEmpty(input);
        validateContainsNickname(input, nicknames);
    }

    private static void validateContainsNickname(String input, Set<String> nicknames) {
        if (!nicknames.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_FOUND_NICKNAME.getMessage());
        }
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static void validateContains(String input) {
        if (!OPTIONS.contains(input)) {
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
