package attendance.dto;

public record AttendanceStatus(
        int attendanceCount,
        int lateCount,
        int absenceCount,
        String subject
) {
}
