package adv2.assignment2.com.fileserver.client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static adv2.util.MyLogger.log;

public class FileClient {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        log("클라이언트 시작");

        try (Scanner scanner = new Scanner(System.in); Socket socket = new Socket(HOST, PORT);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {
            while (true) {
                System.out.println("명령어를 입력하세요 (예: ls <경로>, view <경로>):");
                System.out.print("> ");
                String request = scanner.nextLine();

                if (request.equalsIgnoreCase("exit")) {
                    System.out.println("클라이언트를 종료합니다.");
                    break;
                }

                String[] parts = request.trim().split("\\s+", 2);
                String command = parts[0];
                String path = parts.length > 1 ? parts[1] : "";


                output.writeUTF(command);
                output.writeUTF(path);

                boolean isSuccess = input.readBoolean();

                if (isSuccess) {
                    if (command.equalsIgnoreCase("ls")) {
                        handleListResponse(input);
                    } else if (command.equalsIgnoreCase("view")) {
                        handleViewResponse(input);
                    }
                } else {
                    System.out.println(input.readUTF());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleListResponse(DataInputStream input) throws IOException {
        int count = input.readInt();
        for (int i = 0; i < count; i++) {
            System.out.println(input.readUTF());
        }
    }

    private static void handleViewResponse(DataInputStream input) throws IOException {
        while (true) {
            String line = input.readUTF();

            if (line.equalsIgnoreCase("EOF")) {
                break;
            }

            System.out.println(line);
        }
    }
}
