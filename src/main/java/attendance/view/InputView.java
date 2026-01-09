package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_OPTION = "오늘은 %d월 %d일 금요일입니다. 기능을 선택해 주세요.";
    private static final String INPUT_NICKNAME = "닉네임을 입력해 주세요.";
    private static final String INPUT_ARRIVAL_TIME = "등교 시간을 입력해 주세요.";

    public String readInputOption(int month, int day) {
        return userInput(String.format(INPUT_OPTION, month, day));
    }

    public String readInputNickname() {
        return userInput(INPUT_NICKNAME);
    }

    public String readInputArrivalTime() {
        return userInput(INPUT_ARRIVAL_TIME);
    }

    private String userInput(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
