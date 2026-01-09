package attendance;

import attendance.config.AppConfig;
import attendance.controller.AttendanceController;

public class Application {
    public static void main(String[] args) {
        AttendanceController attendanceController = AppConfig.createController();
        attendanceController.start();
    }
}
