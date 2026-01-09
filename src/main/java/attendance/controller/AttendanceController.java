package attendance.controller;

import attendance.util.FileReaders;
import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
                LocalDateTime dateTime = LocalDateTime.of(2024, 12, 13, 13, 0);
                String option = readOption(dateTime);
                Map<String, List<LocalDateTime>> attendances = FileReaders.readFile();
                if (option.equals("1")) {
                    startOptionOne(dateTime, attendances);
                }
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
                break;
            }
        }
    }

    private void startOptionOne(LocalDateTime dateTime, Map<String, List<LocalDateTime>> attendances) {
        String nickName = inputView.readInputNickname();
        Validator.validateNickName(nickName, attendances.keySet());
    }

    private String readOption(LocalDateTime dateTime) {
        String inputOption = inputView.readInputOption(dateTime.getMonthValue(), dateTime.getDayOfMonth());
        Validator.validateOption(inputOption);
        return inputOption;
    }
}
