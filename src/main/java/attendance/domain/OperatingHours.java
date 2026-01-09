package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum OperatingHours {
    MONDAY(LocalTime.of(13, 0), LocalTime.of(18, 0)),
    OTHER_DAY(LocalTime.of(8, 0), LocalTime.of(18, 0));

    private final LocalTime startTime;
    private final LocalTime endTime;

    OperatingHours(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static OperatingHours findOperatingHours(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
            return MONDAY;
        }
        return OTHER_DAY;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
