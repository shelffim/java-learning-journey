package mid1.assignment2.com.dataparser;

import mid1.assignment2.com.dataparser.service.DataProcessorService;

public class Main {

    public static void main(String[] args) {
        // 여러 줄의 CSV 데이터. 각 필드는 공백을 포함할 수 있음.
        String csvData = "user01, Alice, 30, true\n" +
                "user02, Bob, 25, false\n" +
                "user03, Charlie, 35, true\n" +
                "user04, David, 40, true\n" +
                "user05, Eve, 22, false";

        DataProcessorService processor = new DataProcessorService();

        // CSV 데이터 파싱 및 저장
        processor.parseAndAddUsers(csvData);

        // 분석 결과 출력
        double activeUserAverageAge = processor.calculateActiveUserAverageAge();

        System.out.println("활성 사용자 평균 나이: " + activeUserAverageAge);
    }
}
