# 과제: 유연한 사용자 데이터 마이그레이션 시스템 설계

## 🎯 과제 목표

-   **`List` 컬렉션**의 개념을 이해하고, `ArrayList`와 `LinkedList` 중 어떤 구현체가 이 시스템에 더 적합한지 고민하고 선택한다.
-   **의존관계 주입(DI)**과 **전략 패턴**을 적용하여 핵심 로직(`MigrationService`)과 구체적인 행동(`MigrationStrategy`)의 역할을 분리한다.
-   각 마이그레이션 전략의 **시간 복잡도(Big O)**를 분석하고 코멘트로 남겨, 알고리즘 성능에 대한 이해도를 높인다.
-   불변 객체, `equals`/`hashCode`, 상속 등 이전에 학습한 모든 내용을 종합적으로 활용한다.

## ⚙️ 기능 요구사항 (설계에 집중)

### 1. 사용자 데이터 객체 설계 (`domain` 패키지)
-   **`User` 클래스**:
    -   `id` (String), `name` (String) 필드를 가지는 **불변 객체**로 설계합니다.
    -   `equals()`와 `hashCode()`를 `id`를 기준으로 오버라이딩합니다.

### 2. 마이그레이션 전략 설계 (`strategy` 패키지)
-   **`MigrationStrategy` 인터페이스**:
    -   마이그레이션 전략의 표준을 정의합니다.
    -   `migrate`라는 메서드를 포함하며, 이 메서드가 어떤 파라미터를 받아야 할지, 어떤 값을 반환해야 할지 직접 고민해서 정의하세요.
-   **`DirectMigrationStrategy` 클래스**:
    -   `MigrationStrategy`를 구현합니다.
    -   원본 배열(`User[]`)의 순서대로 새로운 `List`에 사용자들을 추가하는 로직을 구현하세요.
    -   **`// 시간 복잡도: O(??)`** 와 같이 구현 메서드의 시간 복잡도를 주석으로 분석하세요.
-   **`SortedMigrationStrategy` 클래스**:
    -   `MigrationStrategy`를 구현합니다.
    -   원본 배열(`User[]`)의 사용자들을 **`id`를 기준으로 정렬**한 후 새로운 `List`에 추가하는 로직을 구현하세요.
    -   **`// 시간 복잡도: O(??)`** 와 같이 구현 메서드의 시간 복잡도를 주석으로 분석하세요.

### 3. 마이그레이션 실행 서비스 설계 (`service` 패키지)
-   **`LegacySystem` 클래스**:
    -   `getAllUsers` 메서드를 포함하며, `User[]` 배열 형태의 가상의 데이터를 반환합니다.
-   **`MigrationService` 클래스**:
    -   **`DI`**: `MigrationStrategy`와 `List` 구현체(예: `ArrayList` 또는 `LinkedList`)를 **생성자 주입**으로 받도록 설계하세요.
    -   **`migrateUsers` 메서드**: `LegacySystem`에서 데이터를 받아, 주입받은 `MigrationStrategy`를 실행하는 핵심 로직을 구현하세요.
