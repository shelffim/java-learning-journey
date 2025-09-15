# 과제: 나만의 불변 날짜 클래스 `MyLocalDate` 설계

## 🎯 과제 목표

- `final` 키워드와 방어적 복사를 활용하여 **불변(Immutable) 클래스**를 직접 설계하고 구현한다.
- `enum`을 활용하여 휴일과 같은 특정 상수 데이터를 타입-안전(Type-safe)하게 관리하는 방법을 익힌다.
- 날짜 계산(일 더하기, 월/년도 변경 처리) 로직을 직접 구현하며 알고리즘적 사고를 기른다.
- 직접 만든 날짜 클래스를 다른 클래스의 부품으로 사용하여 시스템을 조립하는 능력을 배양한다.

## ⚙️ 기능 요구사항

### 1. `Holiday` 열거형(Enum) 설계 (`time` 패키지)
- 대한민국의 일부 공휴일 정보를 타입-안전하게 관리합니다.
- 각 `enum` 상수는 `month`(월)와 `day`(일) 정보를 가집니다.
- 예시: `NEW_YEAR(1, 1)`, `INDEPENDENCE_DAY(3, 1)`, `CHILDRENS_DAY(5, 5)`

### 2. `MyLocalDate` 클래스 설계 (`time` 패키지)
- **불변 객체**로 설계해야 합니다.
- **필드**: `private final int year`, `month`, `day`
- **메서드**:
    - `public MyLocalDate plusDays(int days)`
        - 현재 날짜에 `days`만큼 더한 **새로운 `MyLocalDate` 객체**를 반환해야 합니다. (기존 객체 상태 변경 금지)
        - **핵심 과제**: 일이 더해졌을 때 월(month)이 바뀌는 경우와 년(year)이 바뀌는 경우를 모두 정확하게 처리해야 합니다. (윤년은 고려하지 않아도 됩니다.)
    - `public boolean isBefore(MyLocalDate other)`: 현재 날짜가 `other` 날짜보다 이전인지 비교합니다.
    - `public boolean isAfter(MyLocalDate other)`: 현재 날짜가 `other` 날짜보다 이후인지 비교합니다.
    - `public boolean isHoliday()`: 오늘이 `Holiday` 열거형에 정의된 공휴일 중 하나인지 확인합니다.
- `toString()`, `equals()`, `hashCode()`를 적절히 오버라이딩합니다.

### 3. `Event` 및 `ScheduleManager` 클래스 설계 (`event` 패키지)
- **`Event` 클래스**: `name` (이벤트명, String)과 `date` (**`MyLocalDate`**) 필드를 가지는 간단한 데이터 객체(DTO)로 설계합니다.
- **`ScheduleManager` 클래스**:
    - `Event[]` 배열을 사용하여 이벤트 목록을 관리합니다.
    - `addEvent(Event event)`: 새로운 이벤트를 추가합니다.
    - `findEventsInMonth(int year, int month)`: 특정 연도와 월에 해당하는 모든 `Event`들을 찾아 `Event[]` 배열로 반환합니다.

### 💡 고민해 볼 점 (핵심 설계 포인트)
- `plusDays` 메서드에서, **각 월의 마지막 날짜(30일, 31일, 28일)는 어떻게 관리**해야 할까요? 배열이나 `switch`문을 활용해 보세요.
- 년, 월, 일을 비교하는 `isBefore`, `isAfter` 메서드를 어떻게 하면 가장 효율적으로 구현할 수 있을까요?
- 불변성을 지키기 위해 `plusDays` 메서드는 왜 반드시 `new MyLocalDate(...)`를 반환해야만 할까요?

