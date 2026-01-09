package attendance.view;

import attendance.dto.AttendanceResult;
import java.time.format.DateTimeFormatter;

public class OutputView {
    DateTimeFormatter KOREAN_FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm");

    public void printAttendance(AttendanceResult attendanceResult) {
        String date = attendanceResult.attendanceDateTime().format(KOREAN_FORMATTER);
        System.out.println(String.format("%s (%s)", date, attendanceResult.attendanceStatus()));
    }

    public void printError(RuntimeException e) {
        System.out.println(e.getMessage());
        printNewLine();
    }

    private static void printNewLine() {
        System.out.print(System.lineSeparator());
    }
}
