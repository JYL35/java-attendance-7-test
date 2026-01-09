package attendance.controller;

import attendance.dto.AttendanceDTO;
import attendance.util.FileReaders;
import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                AttendanceDTO attendanceDTO = createAttendanceDTO();
                String option = readOption(attendanceDTO.dateTime());
                if (option.equals("1")) {
                    startOptionOne(attendanceDTO);
                }
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
                break;
            }
        }
    }

    private AttendanceDTO createAttendanceDTO() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 13, 13, 0);
        LocalTime campusStartTime = LocalTime.of(8, 0);
        LocalTime campusEndTime = LocalTime.of(23, 0);
        Map<String, List<LocalDateTime>> attendances = FileReaders.readFile();
        return new AttendanceDTO(dateTime, campusStartTime, campusEndTime, attendances);
    }

    private void startOptionOne(AttendanceDTO attendanceDTO) {
        String nickName = inputView.readInputNickname();
        Validator.validateNickName(nickName, attendanceDTO.attendances().keySet());
        String inputArrivalTime = inputView.readInputArrivalTime();
        LocalTime arrivalTime = Validator.validateArrivalTime(inputArrivalTime, nickName, attendanceDTO);
    }

    private String readOption(LocalDateTime dateTime) {
        String inputOption = inputView.readInputOption(dateTime.getMonthValue(), dateTime.getDayOfMonth());
        Validator.validateOption(inputOption);
        return inputOption;
    }
}
