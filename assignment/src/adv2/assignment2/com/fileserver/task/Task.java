package adv2.assignment2.com.fileserver.task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static adv2.util.MyLogger.log;

public class Task implements Runnable{

    private final Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                String command = input.readUTF();
                if (command.equals("exit")) {
                    break;
                }
                String path = input.readUTF();

                log("클라이언트 요청: " + command + " " + path);

                if (command.equalsIgnoreCase("ls")) {
                    handleListCommand(path, output);
                } else if (command.equalsIgnoreCase("view")) {
                    handleViewCommand(path, output);
                } else {
                    // 정의되지 않은 명령어 처리
                    output.writeBoolean(false);
                    output.writeUTF("[ERROR} 알 수 없는 명령어입니다: " + command);
                }
            }
        } catch (IOException e) {
            // 클라이언트 연결이 끊어지는 등 통신 오류 처리
            System.out.println("클라이언트와의 연결이 끊어졌습니다: " + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleViewCommand(String pathString, DataOutputStream output) throws IOException {
        Path path = Path.of(pathString);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            output.writeBoolean(false);
            output.writeUTF("[ERROR] 해당 파일을 찾을 수 없거나 디렉토리입니다: " + pathString);
            return;
        }

        output.writeBoolean(true);
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            stream.forEach(line -> {
                try {
                    output.writeUTF(line);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
        // 스트리밍의 끝을 알리는 마커를 전송합니다.
        output.writeUTF("EOF");
    }

    private static void handleListCommand(String pathString, DataOutputStream output) throws IOException {
        try {
            Path path = Path.of(pathString);
            if (!Files.isDirectory(path)) {
                output.writeBoolean(false);
                output.writeUTF("[ERROR] 해당 경로는 디렉토리가 아닙니다: " + pathString);
                return;
            }
            List<String> entries;
            try (Stream<Path> stream = Files.list(path)) {
                entries = stream.map(p -> (Files.isDirectory(p) ? "[D] " : "[F] ") + p.getFileName().toString())
                        .toList();
            }
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
}

