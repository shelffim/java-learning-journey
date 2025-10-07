# 과제: 나만의 미니 원격 파일 탐색기 (DataStream Ver.)

## 🎯 목표

-   `ServerSocket`과 `Socket`을 이용해 멀티스레드 TCP 서버를 구축할 수 있다.
-   클라이언트와 서버가 **`DataStream`**을 이용해 정해진 약속(바이너리 프로토콜)에 따라 통신할 수 있다.
-   서버는 `Files`와 `Path`를 사용해 클라이언트가 요청한 파일 시스템 정보를 조회하고 응답할 수 있다.
-   `try-with-resources`를 활용해 모든 I/O 및 네트워크 리소스를 안전하게 관리할 수 있다.
  - 대용량 파일 전송 시 발생하는 I/O 병목 현상을 분석하고, 버퍼링을 통해 성능을 개선할 수 있다. 

---

## ⭐ 주요 개선 사항: 다운로드 성능 90% 향상

-   **문제 정의:** 초기 구현 시, 100MB 크기의 파일 다운로드에 평균 **325.4초**가 소요되어 사용성이 크게 떨어지는 문제를 발견했습니다.
-   **원인 분석:** 1바이트 단위의 비효율적인 I/O 호출이 반복되어 네트워크 및 디스크 I/O 병목 현상이 발생함을 확인했습니다.
-   **개선 과정:** 8KB 크기의 바이트 버퍼(`byte[] buffer`)를 도입하여 데이터를 덩어리(chunk) 단위로 한 번에 읽고 쓰는 방식으로 I/O 로직을 리팩토링했습니다.
-   **결과:** 평균 다운로드 시간을 **0.178초**로 단축시켜 **약 99.9%%의 성능 향상**을 달성했습니다.

---

## 📝 기능 요구 사항

### [공통] 통신 규약 (Binary Protocol) 정의

클라이언트와 서버는 `DataInputStream`과 `DataOutputStream`을 통해 약속된 순서대로 데이터를 주고받습니다.

-   **클라이언트 → 서버 요청 형식:**
    1.  `command` (`String`): `writeUTF()`로 전송. (`LIST` 또는 `VIEW`)
    2.  `path` (`String`): `writeUTF()`로 전송. (예: `.`, `src/main`, `README.md`)

-   **서버 → 클라이언트 응답 형식:**
    1.  `status` (`boolean`): `writeBoolean()`으로 전송. (성공 시 `true`, 실패 시 `false`)
    2.  **성공 시 (`status`가 `true`):**
        -   `LIST` 명령에 대한 응답:
            1.  목록 개수 (`int`): `writeInt()`로 전송.
            2.  목록 내용 (`String`): 개수만큼 `writeUTF()`로 반복 전송. (예: `[D] src`)
        -   `VIEW` 명령에 대한 응답:
            1.  파일 내용 (`String`): `writeUTF()`로 전송.
        -   `DOWNLOAD` 명령에 대한 응답:
            1. 파일 크기 (`long`): `writeLong()` 으로 전송.
            2. 파일 내용 (`byte[]`): 파일 끝까지 바이트 스트림(raw byte stream) 전송.
    3.  **실패 시 (`status`가 `false`):**
        -   에러 메시지 (`String`): `writeUTF()`로 전송.

### [서버] `FileServer.java`

-   지정된 포트에서 클라이언트의 연결을 무한정 기다립니다.
-   새로운 클라이언트가 접속하면, **별도의 스레드를 생성**하여 해당 클라이언트의 요청을 전담 처리합니다.
-   **클라이언트 처리 로직 (새 스레드에서 수행):**
    1.  클라이언트로부터 `command`와 `path`를 순서대로 읽습니다.
    2.  `command`가 `LIST`이면:
        -   `Files.list()`를 사용해 디렉토리 내용을 조회하고, 성공 여부(`true`), 목록 개수(`int`), 목록 내용(`String`...)을 순서대로 클라이언트에 전송합니다.
    3.  `command`가 `VIEW`이면:
        -   `Files.readString()`을 사용해 파일 내용을 읽고, 성공 여부(`true`), 파일 내용(`String`)을 순서대로 클라이언트에 전송합니다.
    4.  `command` 가 `DOWNLOAD`이면 `Files.newInputStream()`과 버퍼를 사용해 파일의 바이트 스트림을 클라이언트에 전송합니다.
    5.  예외 발생 시, 실패 여부(`false`)와 에러 메시지(`String`)를 순서대로 클라이언트에 전송합니다.

### [클라이언트] `FileClient.java`

-   사용자로부터 콘솔 입력을 받는 무한 루프를 실행합니다.
-   사용자 입력을 분석하여 서버에 보낼 `command`와 `path`를 결정합니다.
-   서버에 접속(`Socket`)하여 `command`와 `path`를 순서대로 전송합니다.
-   서버의 응답을 **약속된 순서대로** 읽습니다.
    1.  가장 먼저 `status`(`boolean`)를 읽습니다.
    2.  `status`가 `true`이면, 성공 데이터를 약속된 순서(개수 → 내용)대로 읽어 출력합니다.
    3.  `status`가 `false`이면, 에러 메시지를 읽어 출력합니다.

---

## 🚀 실행 예시 (클라이언트 콘솔)

명령어를 입력하세요 (예: ls <경로>, view <파일명>, download <파일명>):

ls .
[D] .git
[D] .idea
[F] .gitignore
[F] README.md

명령어를 입력하세요 (예: ls <경로>, view <파일명>, download <파일명>):

download test.zip
test.zip 파일 다운로드 시작...
104857600 바이트 파일 다운로드 완료. (소요 시간: 1.4초)

명령어를 입력하세요 (예: ls <경로>, view <파일명>, download <파일명>):

view README.md

미니 원격 파일 탐색기
이것은 과제용 프로젝트입니다.

명령어를 입력하세요 (예: ls <경로>, view <파일명>, download <파일명>):

ls non-existent-folder
[ERROR] 경로를 찾을 수 없습니다: non-existent-folder

명령어를 입력하세요 (예: ls <경로>, view <파일명>, download <파일명>):

exit
클라이언트를 종료합니다.

---

## 💡 힌트 및 도전 과제

-   **프로토콜 힌트:** 서버가 클라이언트에게 여러 개의 데이터(예: 파일 목록)를 보낼 때는, **실제 데이터를 보내기 전에 먼저 데이터의 개수를
- `writeInt()`로 보내주는 것**이 일반적이고 안정적인 방법입니다.
-   **도전 과제:** `DOWNLOAD <경로>` 명령어를 추가해보세요. `Files.readString()` 대신 `Files.newInputStream()`으로 파일의
- **바이트 스트림**을 읽고, 소켓의 `OutputStream`으로 클라이언트에 그대로 전송하는 기능을 구현해보세요. 
- 클라이언트는 소켓의 `InputStream`으로 바이트 데이터를 받아 `Files.write()`로 파일에 저장해야 합니다.