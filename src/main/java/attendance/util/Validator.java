package attendance.util;

import attendance.constant.ErrorMessage;
import attendance.dto.AttendanceDTO;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
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

    public static LocalTime validateArrivalTime(String input, String nickname,
                                                AttendanceDTO attendanceDTO) {
        validateEmpty(input);
        LocalTime arrivalTime = Parser.parseLocalTime(input);
        validateRangeOfTime(arrivalTime, attendanceDTO);
        validateContainsAttendance(nickname, attendanceDTO);
        return arrivalTime;
    }

    private static void validateContainsAttendance(String nickname, AttendanceDTO attendanceDTO) {
        LocalDateTime localDateTime = attendanceDTO.dateTime();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();

        for (LocalDateTime dateTime : attendanceDTO.attendances().get(nickname)) {
            int dateMonth = dateTime.getMonthValue();
            int dateDay = dateTime.getDayOfMonth();
            if (month == dateMonth && day == dateDay) {
                throw new IllegalArgumentException(ErrorMessage.ALREADY_IN_ATTENDANCE.getMessage());
            }
        }

    }

    public static void validateHoliday(LocalDateTime localDateTime) {
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        boolean publicHoliday = (month == 12 && day == 25);

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || publicHoliday) {
            String today = String.format("%d월 %d일 %s", month, day, dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN));
            throw new IllegalArgumentException(ErrorMessage.ARRIVAL_TIME_IS_HOLIDAY.getCreatedMessage(today));
        }
    }

    private static void validateRangeOfTime(LocalTime arrivalTime, AttendanceDTO attendanceDTO) {
        if (arrivalTime.isAfter(attendanceDTO.operatingHours().getEndTime())) {
            throw new IllegalArgumentException(ErrorMessage.ARRIVAL_TIME_OUT_OF_RANGE.getMessage());
        }
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
}
