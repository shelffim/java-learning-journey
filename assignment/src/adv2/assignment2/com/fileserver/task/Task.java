package adv2.assignment2.com.fileserver.task;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static adv2.util.MyLogger.log;

public class Task implements Runnable{

    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    /*
     Socket과 그로부터 파생된 Stream들은 클라이언트와의 하나의 세션 동안만 사용되는 자원이다.
     try-with-resources를 사용하면 이 블록이 정상 종료 또는 예외 발생이 될 때
     선언된 모든 자원이 자동으로 close() 되도록 보장한다.
     이를 통해 자원 누수를 방지하고, 복잡한 finally 블록을 제거하여 코드를 간결하게 유지한다.
    */
    @Override
    public void run() {
        try (Socket clientSocket = this.socket; // 소켓의 생명주기를 이 스레드와 일치시킴
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

            // 하나의 클라이언트 연결 내에서 여러 명령을 연속적으로 처리하기 위해 무한 루프를 사용한다.
            while (true) {
                // 명령어와 경로를 순서대로 읽는다.
                // 서버 측에서 파싱하는 로직이 단순하고 안정적이 된다.
                String command = input.readUTF();
                String path = input.readUTF();

                if (command.equals("exit")) {
                    // 루프를 종료하면 try-with-resources에 의해 모든 자원이 자동으로 정리된다.
                    break;
                }

                log("클라이언트 요청: " + command + " " + path);

                // 명령어에 따라 적절한 핸들러 메서드를 호출하여 책임을 분리한다.
                if (command.equalsIgnoreCase("ls")) {
                    handleListCommand(path, output);
                } else if (command.equalsIgnoreCase("view")) {
                    handleViewCommand(path, output);
                } else if (command.equalsIgnoreCase("download")) {
                    handleDownloadCommand(path, output);
                } else {
                    // 약속되지 않은 명령어가 들어왔을 경우, 실패와 에러 메시지를 보내기로 약속했다.
                    output.writeBoolean(false);
                    output.writeUTF("[ERROR} 알 수 없는 명령어입니다: " + command);
                }
            }
        } catch (IOException e) {
            // 클라이언트가 비정상적으로 연결을 끊는 등, 통신 자체에 문자게 생겼을 때의 처리
            System.out.println("클라이언트와의 연결이 끊어졌습니다: " + e.getMessage());
        }
    }

    // 'view' 명령어의 비즈니스 로직을 처리한다.
    // 파일을 스트리밍 방식으로 읽어 클라이언트에게 전송한다.
    private static void handleViewCommand(String pathString, DataOutputStream output) throws IOException {
        Path path = Path.of(pathString);

        // 파일을 실제로 읽기 전에, 요청이 유효한지 먼저 검사한다.
        // 불필요한 I/O 작업을 막고, 클라이언트에게 더 명확한 에러 메시지를 준다.
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            output.writeBoolean(false);
            output.writeUTF("[ERROR] 해당 파일을 찾을 수 없거나 디렉토리입니다: " + pathString);
            return;
        }

        output.writeBoolean(true);
        // 대용량 파일도 메모리 문제 없이 처리하기 위해, Stream API를 사용한 스트리밍 방식을 적용한다.
        // try-with-resources를 사용해 파일 스트림 지원이 자동으로 닫히도록 보장한다.
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            // forEach의 람다 표현식 내에서는 Checked Exception(IOException)을 직접 던질 수 없다.
            // UncheckedIOException으로 감싸서 던져, 스트림 처리를 중단시키고
            // 바깥의 catch 블록에서 처리하도록 유도하는 표준적인 패턴이다.
            stream.forEach(line -> {
                try {
                    output.writeUTF(line);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
        // 클라이언트가 파일의 끝을 알 수 있도록, 약속된 종료 마커를 전송한다.
        output.writeUTF("EOF");
    }

    // 'ls' 명령어의 비즈니스 로직을 처리한다.
    // 디렉토리 목록을 읽어 클라이언트에게 전송한다.
    private static void handleListCommand(String pathString, DataOutputStream output) throws IOException {
        // 파일 시스템 접근 시 발생할 수 있는 모든 I/O 예외를 여기서 잡아,
        // 서버 스레드가 비정상 종료되지 않고 클라이언트에세 정상적인 실패 응답을 보내도록 한다.
        try {
            Path path = Path.of(pathString);
            if (!Files.isDirectory(path)) {
                output.writeBoolean(false);
                output.writeUTF("[ERROR] 해당 경로는 디렉토리가 아닙니다: " + pathString);
                return;
            }
            List<String> entries;
            try (Stream<Path> stream = Files.list(path)) {
                // Stream API의 map과 toList를 사용하여, Path 객체의 스트림을
                // "[D] adv2"와 같은 형식의 문자열 리스트로 간결하게 변환한다.
                entries = stream.map(p -> (Files.isDirectory(p) ? "[D] " : "[F] ") + p.getFileName().toString())
                        .toList();
            }
            // 성공, 목록 개수, 목록 내용 순으로 전송한다.
            output.writeBoolean(true);
            output.writeInt(entries.size());
            for (String entry : entries) {
                output.writeUTF(entry);
            }
        } catch (IOException e) {
            output.writeBoolean(false);
            output.writeUTF("[ERROR] 디렉토리 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 'download' 명령어의 비즈니스 로직을 처리한다.
    // 파일을 읽어 바이트 스트림을 클라이언트에게 전송한다.
    private void handleDownloadCommand(String pathString, DataOutputStream output) throws IOException {
        Path path = Path.of(pathString);

        // 파일을 실제로 읽기 전에, 요청이 유효한지 먼저 검사한다.
        // 불필요한 I/O 작업을 막고, 클라이언트에게 더 명확한 에러 메시지를 준다.
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            output.writeBoolean(false);
            output.writeUTF("[ERROR] 해당 파일을 찾을 수 없거나 디렉토리입니다: " + pathString);
            return;
        }

        // 파일을 읽기전에 파일의 크기를 구한다.
        long fileSize = Files.size(path);

        // 성공 여부와 파일 크기를 보낸다.
        output.writeBoolean(true);
        output.writeLong(fileSize);

        // 파일을 열고, 1바이트씩 읽어서 보낸다.
        try (InputStream inputStream = Files.newInputStream(path)) {

            int data;
            while ((data = inputStream.read()) != -1 ) {
                output.write(data);
            }
        }
    }
}

