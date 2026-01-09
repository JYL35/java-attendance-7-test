package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_OPTION = "오늘은 %d월 %d일 금요일입니다. 기능을 선택해 주세요.";

    public String readPurchaseAmount(int month, int day) {
        return userInput(String.format(INPUT_OPTION, month, day));
    }

    private String userInput(String message) {
        System.out.println(message);
        return Console.readLine();
    }
}
