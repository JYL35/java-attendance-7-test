package attendance.controller;

import attendance.domain.OperatingHours;
import attendance.dto.AttendanceDTO;
import attendance.dto.AttendanceResult;
import attendance.dto.CrewAttendance;
import attendance.service.AttendanceService;
import attendance.util.FileReaders;
import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AttendanceController {

    private final AttendanceService attendanceService;
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(AttendanceService attendanceService, InputView inputView, OutputView outputView) {
        this.attendanceService = attendanceService;
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

                if (option.equals("2")) startOptionTwo(attendanceDTO);
            } catch (IllegalArgumentException e) {
                outputView.printError(e);
                throw new IllegalArgumentException(e);
            }
        }
    }

    private void startOptionTwo(AttendanceDTO attendanceDTO) {
        Validator.validateHoliday(attendanceDTO.dateTime());
        String nickName = inputView.readInputNickname();
        Validator.validateNickName(nickName, attendanceDTO.attendances().keySet());
        CrewAttendance crewAttendance = attendanceService.createCrewAttendance(attendanceDTO, nickName);
    }

    private AttendanceDTO createAttendanceDTO() {
        // LocalDateTime dateTime = LocalDateTime.of(2024, 12, 13, 13, 0);
        LocalDateTime dateTime = DateTimes.now();
        OperatingHours operatingHours = OperatingHours.findOperatingHours(dateTime.getDayOfWeek());
        Map<String, List<LocalDateTime>> attendances = FileReaders.readFile();
        return new AttendanceDTO(dateTime, operatingHours, attendances);
    }

    private void startOptionOne(AttendanceDTO attendanceDTO) {
        Validator.validateHoliday(attendanceDTO.dateTime());
        String nickName = inputView.readInputNickname();
        Validator.validateNickName(nickName, attendanceDTO.attendances().keySet());
        String inputArrivalTime = inputView.readInputArrivalTime();
        LocalTime arrivalTime = Validator.validateArrivalTime(inputArrivalTime, nickName, attendanceDTO);
        AttendanceResult attendanceResult = attendanceService.addAttendance(attendanceDTO, nickName, arrivalTime);
        outputView.printAttendance(attendanceResult);
    }

    private String readOption(LocalDateTime dateTime) {
        String inputOption = inputView.readInputOption(dateTime.getMonthValue(), dateTime.getDayOfMonth(), dateTime.getDayOfWeek());
        Validator.validateOption(inputOption);
        return inputOption;
    }
}
