package attendance.config;

import attendance.controller.AttendanceController;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AppConfig {

    public static AttendanceController createController() {
        return new AttendanceController(
                new InputView(), new OutputView()
        );
    }
}
