# 과제: 웹 브라우저 기록 및 북마크 관리 시스템

## 🎯 과제 목표

-   **`Map`**을 사용하여 **Key-Value** 구조의 데이터를 효율적으로 저장하고 검색하는 방법을 익힌다. (북마크 관리)
-   **`Deque`**를 **`Stack`**처럼 활용하여 **후입선출(LIFO)** 자료 구조를 구현하는 방법을 익힌다. ('뒤로 가기' 기능)
-   **`Comparable`**과 **`Comparator`**를 모두 사용하여, 객체의 기본 정렬 기준과 외부 정렬 기준을 각각 정의하고 적용하는 방법을 익힌다.
-   `List`, `Set`, 불변 객체, DI, 예외 처리 등 이전에 학습한 모든 내용을 종합적으로 활용하여 하나의 완성된 시스템을 설계한다.

## ⚙️ 기능 요구사항 (설계에 집중)

### 1. 데이터 객체 설계 (`domain` 패키지)
-   **`WebPage` 클래스**:
    -   `url` (String), `title` (String) 필드를 가지는 **불변 객체**로 설계합니다.
-   **`Bookmark` 클래스**:
    -   `url` (String), `name` (String) 필드를 가지는 **불변 객체**로 설계합니다.
    -   **`Comparable`** 인터페이스를 구현하여, **이름(`name`)을 기준**으로 오름차순 정렬이 되도록 기본 정렬 순서를 정의하세요.
    -   `equals()`와 `hashCode()`는 `url`을 기준으로 오버라이딩합니다.

### 2. 정렬 기준 클래스 설계 (`service.sort` 패키지)
-   **`UrlComparator` 클래스**:
    -   **`Comparator`** 인터페이스를 구현합니다.
    -   `Bookmark` 객체 두 개를 받아, **URL을 기준**으로 오름차순 정렬하도록 비교 로직을 작성하세요.

### 3. `BrowserManager` 클래스 설계 (`service` 패키지)
-   **`BrowserManager` 클래스**:
    -   **`DI`**: `Deque`와 `Map` 구현체를 **생성자 주입**으로 받도록 설계하세요.
        -   '뒤로 가기' 스택을 위한 `Deque`
        -   북마크 저장을 위한 `Map` (Key: URL, Value: Bookmark 객체)
    -   **`visitPage` 메서드**: `WebPage`를 받아 '뒤로 가기' 스택에 추가합니다.
    -   **`goBack` 메서드**: 스택에서 가장 최근 페이지를 꺼내(pop) 반환합니다. 스택이 비어있으면 `null`을 반환하세요.
    -   **`addBookmark` 메서드**: `Bookmark`를 `Map`에 추가합니다. URL이 이미 존재하면 덮어씁니다.
    -   **`getBookmarksSortedByName` 메서드**: 북마크들을 **이름순(기본 정렬)**으로 정렬하여 `List`로 반환합니다.
    -   **`getBookmarksSortedByUrl` 메서드**: 북마크들을 **URL순(외부 정렬)**으로 정렬하여 `List`로 반환합니다.