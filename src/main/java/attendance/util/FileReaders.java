package attendance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaders {
    private static final String PATH = "D:\\workplace\\woowacourse\\final coding test\\practice\\"
            + "java-attendance-7-test\\src\\main\\resources\\attendances.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Map<String, List<LocalDateTime>> readFile() {
        File file = new File(PATH);

        if (!file.isFile() || !file.canRead()) {
            throw new IllegalArgumentException("파일을 읽을 수 없습니다.");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return fileToList(bufferedReader);
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는 중 오류가 발생했습니다.");
        }
    }

    private static Map<String, List<LocalDateTime>> fileToList(BufferedReader bufferedReader) throws IOException {
        Map<String, List<LocalDateTime>> attendances = new HashMap<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] type = line.split(",");
            String nickName = type[0];
            LocalDateTime dateTime = LocalDateTime.parse(type[1], formatter);
            if (!attendances.getOrDefault(nickName, new ArrayList<>()).isEmpty()) {
                attendances.get(nickName).add(dateTime);
                continue;
            }
            List<LocalDateTime> temp = new ArrayList<>();
            temp.add(dateTime);
            attendances.put(nickName, temp);
        }

        return Map.copyOf(attendances);
    }
}
