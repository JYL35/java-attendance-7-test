package attendance.service;

import attendance.domain.OperatingHours;
import attendance.dto.AttendanceDTO;
import attendance.dto.AttendanceResult;
import attendance.dto.AttendanceStatus;
import attendance.dto.CrewAttendance;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {

    public AttendanceResult addAttendance(AttendanceDTO attendanceDTO, String nickName, LocalTime arrivalTime) {
        LocalDateTime localDateTime = LocalDateTime.of(attendanceDTO.dateTime().toLocalDate(), arrivalTime);
        attendanceDTO.putAttendance(nickName, localDateTime);
        return new AttendanceResult(localDateTime, checkAttendanceStatus(attendanceDTO.operatingHours().getStartTime(), arrivalTime));
    }

    public CrewAttendance createCrewAttendance(AttendanceDTO attendanceDTO, String nickname) {
        return createAttendanceResult(attendanceDTO.attendances().get(nickname));

    }

    private CrewAttendance createAttendanceResult(List<LocalDateTime> localDateTimes) {
        List<AttendanceResult> attendanceResults = new ArrayList<>();
        int attendanceCount = 0;
        int lateCount = 0;
        int absenceCount = 0;
        for (LocalDateTime localDateTime : localDateTimes) {
            String status = checkAttendanceStatus(OperatingHours.findOperatingHours(localDateTime.getDayOfWeek()).getStartTime(),
                    localDateTime.toLocalTime());
            if (status.equals("결석")) absenceCount++;
            if (status.equals("지각")) lateCount++;
            if (status.equals("출석")) attendanceCount++;
            attendanceResults.add(new AttendanceResult(localDateTime, status));
        }
        AttendanceStatus attendanceStatus = createAttendanceStatus(attendanceCount, lateCount, absenceCount);
        return new CrewAttendance(attendanceResults, attendanceStatus);
    }

    private AttendanceStatus createAttendanceStatus(int attendanceCount, int lateCount, int absenceCount) {
        int tempLateCount = lateCount % 3;
        int tempAbsenceCount = absenceCount + lateCount / 3;
        String status = "좋음";
        if (tempAbsenceCount > 5) status = "제적";
        if (tempAbsenceCount >= 3) status = "면담";
        if (tempAbsenceCount >= 2) status = "경고";
        return new AttendanceStatus(attendanceCount, tempLateCount, tempAbsenceCount, status);
    }

    private String checkAttendanceStatus(LocalTime campusStartTime, LocalTime arrivalTime) {
        if (arrivalTime.isAfter(campusStartTime.plusMinutes(30))) {
            return "결석";
        }
        if (arrivalTime.isAfter(campusStartTime.plusMinutes(5))) {
            return "지각";
        }
        return "출석";
    }
}
