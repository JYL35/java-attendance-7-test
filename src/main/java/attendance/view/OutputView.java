package attendance.view;

import attendance.dto.AttendanceResult;
import attendance.dto.AttendanceStatus;
import attendance.dto.CrewAttendance;
import java.time.format.DateTimeFormatter;

public class OutputView {
    DateTimeFormatter KOREAN_FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm");

    public void printCrewAttendance(CrewAttendance crewAttendance) {
        printNewLine();
        System.out.println("이번 달 빙티의 출석 기록입니다.");
        printNewLine();
        for (AttendanceResult attendanceResult : crewAttendance.AttendanceRecord()) {
            printAttendance(attendanceResult);
        }
        printNewLine();
        AttendanceStatus attendanceStatus = crewAttendance.attendanceStatus();
        System.out.println(String.format("출석: %d회", attendanceStatus.attendanceCount()));
        System.out.println(String.format("지각: %d회", attendanceStatus.lateCount()));
        System.out.println(String.format("결석: %d회", attendanceStatus.absenceCount()));
        printNewLine();
        if (!attendanceStatus.subject().equals("좋음")) {
            System.out.println(String.format("%s 대상자입니다.", attendanceStatus.subject()));
        }
    }

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
