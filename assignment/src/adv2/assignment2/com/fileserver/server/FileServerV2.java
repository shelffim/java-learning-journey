package adv2.assignment2.com.fileserver.server;

import adv2.assignment2.com.fileserver.task.Task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static adv2.util.MyLogger.log;

public class FileServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) {
        log("서버 시작");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 클라이언트 연결 기다림
                Socket socket = serverSocket.accept();
                log("클라이언트 연결 성공" + socket.getInetAddress());

                // 연결된 클라이언트마다 별도의 스레드를 생성하여 처리를 위임
                Task task = new Task(socket);
                Thread thread = new Thread(task);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
