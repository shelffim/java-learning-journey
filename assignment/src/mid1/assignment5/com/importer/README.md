# 과제: 견고한 파일 기반 사용자 임포트 시스템 설계

## 🎯 과제 목표

- `try-catch-finally` 구조를 사용하여 예외 상황을 처리하고, 어떤 경우에도 자원이 안전하게 해제되도록 보장하는 방법을 익힌다.
- 특정 예외 상황을 명확하게 표현하기 위해 직접 **Checked Exception**을 정의하고, `throw`와 `throws`를 활용하여 예외를 처리하는 흐름을 설계한다.
- 데이터 처리 중 일부 데이터에 문제가 있더라도, 전체 작업이 중단되지 않고 나머지 데이터를 계속 처리하는 견고한 로직을 구현한다.
- 지금까지 배운 모든 객체 지향 원칙(역할 분리, 불변성, 다형성 등)을 종합적으로 적용한다.

## ⚙️ 기능 요구사항

### 1. Custom Checked Exception 2개 정의 (`exception` 패키지)
- `DataLoadException extends Exception`: 데이터 소스(파일)를 열거나 읽을 수 없는 치명적인 오류 상황에 사용할 예외.
- `InvalidDataFormatException extends Exception`: 데이터 한 줄의 형식이 잘못되었을 때(예: 필드 개수 부족, 숫자 형식 오류) 사용할 예외.

### 2. `DataSource` 클래스 설계 (`service` 패키지)
- 파일 I/O를 배우기 전이므로, 파일을 흉내 내는 클래스를 만듭니다.
- **필드**: `private final String[] data`
- **메서드**:
    - `public String[] read()`: 저장된 데이터를 반환합니다.
    - `public void close()`: `"데이터 소스를 닫습니다."` 메시지를 출력하며 자원이 해제되었음을 알립니다.

### 3. `User` 및 `UserRepository` 클래스 설계 (`data` 패키지)
- `User` 클래스는 `id`, `name`, `age`를 가진 불변 객체로 만듭니다.
- `UserRepository`는 `User[]` 배열을 관리하며, `addUser` 시 배열이 꽉 차면 `Arrays.copyOf`를 사용해 2배로 증설하는 로직을 포함합니다. `getAllUsers()` 메서드도 추가해주세요.

### 4. `UserService` 클래스 설계 (`service` 패키지)
- **필드**: `private final UserRepository repository` (생성자 주입)
- **`public void importUsers(DataSource dataSource) throws DataLoadException`**:
    - 이 메서드는 임포트 과정의 **총괄 책임자**입니다.
    - **자원 관리**: `dataSource`는 반드시 `finally` 블록에서 `close()` 되어야 합니다.
    - **예외 처리**:
        1.  `try` 블록 안에서 `dataSource`가 `null`이면, 직접 `DataLoadException`을 `throw` 합니다.
        2.  `dataSource.read()`를 통해 데이터를 읽어옵니다.
        3.  `for`문으로 각 데이터 줄을 처리합니다.
        4.  각 줄을 처리하는 로직은 **내부 `try-catch` 블록**으로 감싸서, 한 줄에 문제가 생겨도(`InvalidDataFormatException` 발생) 전체 임포트가 중단되지 않게 합니다.
        5.  데이터 한 줄을 파싱하여 `User` 객체를 만드는 로직은 `private User parseUser(String line) throws InvalidDataFormatException` 헬퍼 메서드로 분리합니다.
            - 이 메서드 안에서 `line`의 형식이 잘못되었으면 `InvalidDataFormatException`을 `throw` 합니다.