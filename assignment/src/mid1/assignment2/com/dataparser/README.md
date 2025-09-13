⚙️ 기능 요구사항

### 1. `UserData` 클래스 설계 (domain 패키지)
- CSV의 한 행(사용자 한 명) 데이터를 담을 클래스입니다.
- **불변 객체**로 설계해야 합니다.
- **필드**:
    - `id` (String)
    - `name` (String)
    - `age` (**Integer**) - 래퍼 클래스 타입
    - `isActive` (**Boolean**) - 래퍼 클래스 타입
- `toString()`, `equals()`, `hashCode()`를 `id` 기준으로 오버라이딩합니다.

### 2. `DataProcessorService` 클래스 설계 (service 패키지)
- CSV 데이터를 처리하고 분석하는 핵심 로직을 담당합니다.
- 내부에 파싱된 `UserData` 객체들을 저장할 `UserData[]` 배열을 관리해야 합니다.
- **메서드**:
    - **`public void parseAndAddUsers(String csvData)`**:
        1.  입력받은 `csvData`(여러 줄의 문자열)를 줄바꿈 문자(`\n`)를 기준으로 한 줄씩 나눕니다. (`String.split()`)
        2.  각 줄을 쉼표(`,`)를 기준으로 다시 나눕니다. (`String.split()`)
        3.  나눠진 문자열 데이터의 앞뒤 공백을 제거합니다. (`String.trim()`)
        4.  문자열로 된 나이와 활성 상태를 각각 **`Integer.parseInt()`**, **`Boolean.parseBoolean()`** 을 사용해 래퍼 타입으로 변환합니다.
        5.  변환된 데이터로 `UserData` 객체를 생성하여 내부 배열에 저장합니다.
    - **`public double calculateActiveUserAverageAge()`**:
        1.  저장된 모든 사용자 중 `isActive`가 `true`인 사용자들만 대상으로 합니다.
        2.  이들의 평균 나이를 계산하여 `double` 타입으로 반환합니다. (오토언박싱 활용)

## 🖥️ 실행 메인 클래스 (`Main.java`)

아래 코드를 사용하여 직접 만든 클래스들이 올바르게 동작하는지 확인하세요.

```java
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