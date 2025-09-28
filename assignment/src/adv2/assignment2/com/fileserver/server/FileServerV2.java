package adv2.assignment2.com.fileserver.server;

import adv2.assignment2.com.fileserver.task.Task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static adv2.util.MyLogger.log;

public class FileServerV2 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결" + socket);

                Task task = new Task(socket);
                Thread thread = new Thread(task);
                thread.start();
            }
        }
    }
}
