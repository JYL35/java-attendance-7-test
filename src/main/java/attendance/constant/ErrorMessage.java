package attendance.constant;

public enum ErrorMessage {

    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    ARRIVAL_TIME_OUT_OF_RANGE("캠퍼스 운영 시간에만 출석이 가능합니다."),
    ALREADY_IN_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),
    ARRIVAL_TIME_IS_HOLIDAY("은 등교일이 아닙니다."),
    NOT_FOUND_NICKNAME("등록되지 않은 닉네임입니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public String getCreatedMessage(String str) {
        return PREFIX + str + message;
    }
}