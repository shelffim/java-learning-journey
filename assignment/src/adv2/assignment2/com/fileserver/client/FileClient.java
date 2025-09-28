package adv2.assignment2.com.fileserver.client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static adv2.util.MyLogger.log;

public class FileClient {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost",PORT);DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {
            while (true) {
                System.out.println("명령어를 입력하세요 (예: ls <경로>, view <경로>):");

                String commandAndPath = scanner.nextLine();
                output.writeUTF(commandAndPath);
                if (commandAndPath.equals("exit")) {
                    System.out.println("클라이언트를 종료합니다.");
                    break;
                }

                boolean bool = input.readBoolean();

                if (bool) {
                    int count = input.readInt();
                    for (int i = 0; i < count; i++) {
                        String result = input.readUTF();
                        System.out.println(result);
                    }
                } else {
                    String message = input.readUTF();
                    String[] split = commandAndPath.split(" ");
                    if (split.length > 1) {
                        message += commandAndPath.split(" ")[1];
                    }
                    System.out.println(message);
                }
            }
        }
    }
}
