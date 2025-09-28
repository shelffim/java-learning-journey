package adv2.assignment2.com.fileserver.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static adv2.util.MyLogger.log;

public class FileServer {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);

        try ( Socket socket = serverSocket.accept();
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                try {
                    String[] commandAndPath = input.readUTF().split(" ");
                    String command = commandAndPath[0];
                    if (command.equals("exit")) {
                        break;
                    }
                    if (commandAndPath.length == 1) {
                        output.writeBoolean(false);
                        output.writeUTF("명령어를 잘못 입력하였습니다.");
                        System.out.println();
                        continue;
                    }
                    String path = commandAndPath[1];
                    Path findPath = Path.of(path);
                    log("요청: " + command + ", 경로: " + path);
                    if (command.equals("ls")) {
                        findList(findPath, output);
                    } else if (command.equals("view")) {
                        findFile(findPath, output);
                    }
                } catch (NoSuchFileException e) {
                    output.writeBoolean(false);
                    output.writeUTF("[ERROR] 경로를 찾을 수 없습니다: ");
                }
            }
        }
    }

    private static void findFile(Path findPath, DataOutputStream output) throws IOException {
        List<String> lines = Files.readAllLines(findPath);
        output.writeBoolean(true);
        output.writeInt(lines.size());
        for (String line : lines) {
            output.writeUTF(line);
        }
    }

    private static void findList(Path findPath, DataOutputStream output) throws IOException {
        Stream<Path> pathStream = Files.list(findPath);
        List<Path> list = pathStream.toList();
        output.writeBoolean(true);
        output.writeInt(list.size());
        for (Path p : list) {
            String result = (Files.isRegularFile(p) ? "[F]" : "[D]") + " " + p.getFileName();
            output.writeUTF(result);
        }
    }
}
