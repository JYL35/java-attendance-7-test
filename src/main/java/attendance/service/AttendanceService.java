package attendance.service;

import attendance.dto.AttendanceDTO;
import attendance.dto.AttendanceResult;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceService {

    public AttendanceResult addAttendance(AttendanceDTO attendanceDTO, String nickName, LocalTime arrivalTime) {
        LocalDateTime localDateTime = LocalDateTime.of(attendanceDTO.dateTime().toLocalDate(), arrivalTime);
        attendanceDTO.putAttendance(nickName, localDateTime);
        return new AttendanceResult(localDateTime, checkAttendanceStatus(attendanceDTO, arrivalTime));
    }

    private String checkAttendanceStatus(AttendanceDTO attendanceDTO, LocalTime arrivalTime) {
        if (arrivalTime.isBefore(attendanceDTO.campusStartTime()) || arrivalTime.equals(attendanceDTO.campusStartTime())) {
            return "출석";
        }
        return "지각";
    }
}
