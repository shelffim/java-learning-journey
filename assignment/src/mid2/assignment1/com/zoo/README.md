# 과제: 나만의 동물원 관리 시스템 설계

## 🎯 과제 목표

-   **제네릭 클래스**를 사용하여 특정 타입만 담을 수 있는 타입-안전한 컨테이너(`Cage`)를 설계한다.
-   **타입 매개변수 제한 (`extends`)**을 활용하여 특정 타입의 메서드를 안전하게 사용하는 **제네릭 메서드**를 구현한다.
-   **와일드카드 (`? extends T`)**를 사용하여 다양한 타입의 제네릭 객체를 읽기 전용으로 유연하게 처리하는 메서드를 설계한다.
-   **와일드카드 (`? super T`)**를 사용하여 특정 타입의 데이터를 안전하게 추가(쓰기)할 수 있는 메서드를 설계한다.
-   상속, 다형성, `List` 등 이전에 배운 개념을 종합적으로 적용하여 전체 시스템을 완성한다.

## ⚙️ 기능 요구사항

### 1. 동물 클래스 계층 설계 (`domain` 패키지)
-   `Animal` 클래스: `name` (String) 필드를 가지며, `sound()`라는 추상 메서드를 가집니다.
-   `Dog` 클래스: `Animal`을 상속받으며, "멍멍"을 출력하는 `sound()` 메서드를 구현합니다.
-   `Cat` 클래스: `Animal`을 상속받으며, "야옹"을 출력하는 `sound()` 메서드를 구현합니다.

### 2. `Cage<T>` 제네릭 클래스 설계 (`service` 패키지)
-   동물을 한 마리만 담을 수 있는 우리(Cage)입니다.
-   **`T extends Animal`** 과 같이 타입 매개변수에 제약을 걸어 **`Animal`의 자식 타입만** 들어올 수 있도록 설계합니다.
-   **필드**: `private T animal`
-   **메서드**: `add(T animal)`, `getAnimal()`, `makeSound()` (내부 동물의 `sound()` 호출)

### 3. `ZooManager` 클래스 설계 (`service` 패키지)
-   동물원을 관리하는 핵심 로직을 담습니다.
-   **제네릭 메서드**:
    -   `public static <T extends Animal> void printAnimalName(Cage<T> cage)`: 어떤 동물이든 상관없이 우리(`Cage`)를 받아 그 동물의 이름을 출력합니다.
-   **와일드카드 활용 메서드**:
    -   `public static void moveAnimal(Cage<? extends Animal> fromCage, Cage<? super Animal> toCage)`:
        -   `fromCage`에서는 어떤 동물이든 **꺼내오기(read)**만 가능합니다. (`? extends Animal`)
        -   `toCage`에는 `Animal` 타입의 동물을 **넣기(write)**만 가능합니다. (`? super Animal`)
        -   `fromCage`에서 동물을 꺼내 `toCage`로 옮기는 로직을 구현합니다.