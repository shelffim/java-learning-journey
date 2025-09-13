package mid1.assignment2.com.dataparser.service;

import mid1.assignment2.com.dataparser.domain.UserData;

public class DataProcessorService {
    // 빈 배열로 초기화하여 NPE 방지
    UserData[] userData = new UserData[0];

    // 한 줄 파싱
    private UserData parseLineToUserData(String line) {
        String[] fields = line.split(",");

        String id = fields[0].trim();
        String name = fields[1].trim();
        Integer age = Integer.parseInt(fields[2].trim());
        Boolean isActive = Boolean.parseBoolean(fields[3].trim());

        return new UserData(id, name, age, isActive);
    }

    public void parseAndAddUsers(String csvData) {
        String[] lines = csvData.split("\n");
        this.userData = new UserData[lines.length]; // 필드 할당

        // userData에 대입
        for (int i = 0; i < lines.length; i++) {
            this.userData[i] = parseLineToUserData(lines[i]);
        }
    }

    public double calculateActiveUserAverageAge() {
        double sum = 0.0;
        int count = 0;
        double average;
        if (userData.length == 0) {
            return 0.0;
        }
        for (int i = 0; i < userData.length; i++) {
            if (userData[i].getActive()) {
                sum += userData[i].getAge();
                count++;
            }
        }
        average = sum / count;
        return average;
    }
}
