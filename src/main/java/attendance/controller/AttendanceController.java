package attendance.controller;

import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceController {

    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        while (true) {
            try {
                LocalDate date = LocalDate.of(2024, 12, 13);
                String option = readOption(date);
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
                break;
            }
        }
    }

    private String readOption(LocalDate date) {
        String inputOption = inputView.readInputOption(date.getMonthValue(), date.getDayOfMonth());
        Validator.validateOption(inputOption);
        return inputOption;
    }
}
