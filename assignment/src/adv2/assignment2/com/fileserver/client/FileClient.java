package adv2.assignment2.com.fileserver.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static adv2.util.MyLogger.log;

public class FileClient {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        log("클라이언트 시작");

        /*
        사용자의 입력을 받고, 서버와 통신하는 모든 자원을
        try-with-resources로 관리하여, 프로그램 종료 또는 예외 발생 시 자동으로 해제된다.
        리소스 누수를 방지한다.
        */
        try (Scanner scanner = new Scanner(System.in); Socket socket = new Socket(HOST, PORT);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {
            // 사용자가 'exit'를 입력하기 전까지 계속해서 명령을 내릴 수 있는 대화형 세션을 유지하기 위해
            // 무한 루프를 사용한다.
            while (true) {

                System.out.println("명령어를 입력하세요 (예: ls <경로>, view <경로>):");
                System.out.print("> ");
                String request = scanner.nextLine();

                if (request.equalsIgnoreCase("exit")) {
                    System.out.println("클라이언트를 종료합니다.");
                    break;
                }

                // 사용자의 입력을 명령어와 나머지로 분리한다.
                // "\\s+" 정규식은 하나 이상의 연속된 공백을 의미하며, 2는 최대 두 부분으로 나누라는 의미이다.
                // 불규칙한 공백이 있어도 안정적으로 파싱할 . 있다.
                String[] parts = request.trim().split("\\s+", 2);
                String command = parts[0];
                String path = parts.length > 1 ? parts[1] : "";


                // 명령어와 경로를 순서대로 전송한다.
                output.writeUTF(command);
                output.writeUTF(path);

                // 서버는 항상 응답의 첫 부분으로 성공 여부를 보내기로 약속되어 있다.
                boolean isSuccess = input.readBoolean();

                if (isSuccess) {
                    // 클라이언트는 자신이 보낸 명령어에 따라 서버가 보낼 응답의 구조가 다르다는 것을 알고 있다.
                    // 'ls'는 (int, String...) 형식, 'view'는 (String ... "EOF") 형식이다.
                    // 보냈던 명령어를 기준으로 응답 처리 로직을 분기한다.
                    if (command.equalsIgnoreCase("ls")) {
                        handleListResponse(input);
                    } else if (command.equalsIgnoreCase("view")) {
                        handleViewResponse(input);
                    }
                } else {
                    // isSuccess가 false일 경우, 서버는 항상 다음에 에러 메시지를 보내기로 약속했다.
                    System.out.println(input.readUTF());
                }
            }
        } catch (IOException e) {
            // 서버와의 연결이 끊어지거나, 통신 프로토콜이 맞지 않을 때 발생하는 예외를 처리한다.
            throw new RuntimeException(e);
        }
    }

    // 'ls' 명령어에 대한 서버의 성공 응답을 처리한다.
    private static void handleListResponse(DataInputStream input) throws IOException {
        // 서버가 가장 먼저 보낸 '목록의 개수'를 읽는다.
        int count = input.readInt();
        // 개수만큼 반복하며 파일 목록을 읽어와 출력한다.
        for (int i = 0; i < count; i++) {
            System.out.println(input.readUTF());
        }
    }

    // 'view' 명령어에 대한 서버의 성공 응답을 처리한다.
    private static void handleViewResponse(DataInputStream input) throws IOException {
        // 서버가 "EOF"라는 스트림 종료 신호를 보낼 때까지 계속해서 라인을 읽어온다.
        while (true) {
            String line = input.readUTF();
            if (line.equalsIgnoreCase("EOF")) { // 약속된 스트림 종료 마커
                break;
            }
            System.out.println(line);
        }
    }
}
