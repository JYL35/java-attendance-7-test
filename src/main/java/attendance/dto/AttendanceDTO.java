package attendance.dto;

import attendance.domain.OperatingHours;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public record AttendanceDTO(
        LocalDateTime dateTime,
        OperatingHours operatingHours,
        Map<String, List<LocalDateTime>> attendances
) {
    public void putAttendance(String nickName, LocalDateTime localDateTime) {
        attendances.get(nickName).add(localDateTime);
    }
}
