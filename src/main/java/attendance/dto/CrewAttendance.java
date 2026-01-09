package attendance.dto;

import java.util.List;

public record CrewAttendance(
        List<AttendanceResult> AttendanceRecord,
        AttendanceStatus attendanceStatus
) {
}
