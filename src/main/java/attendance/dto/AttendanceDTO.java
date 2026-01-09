package attendance.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public record AttendanceDTO(
        LocalDateTime dateTime,
        LocalTime campusStartTime,
        LocalTime campusEndTime,
        Map<String, List<LocalDateTime>> attendances
) {

}
