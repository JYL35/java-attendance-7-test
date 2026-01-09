package attendance.dto;

import java.time.LocalDateTime;

public record AttendanceResult(
        LocalDateTime attendanceDateTime,
        String attendanceStatus
) {
}
