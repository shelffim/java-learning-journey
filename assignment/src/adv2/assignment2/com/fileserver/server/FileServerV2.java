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
            /*
            서버는 종료되지 않고 계속해서 새로운 클라이언트의 접속을 받아야 한다.
            각 클라이언트의 요청 처리는 파일 I/O 등으로 인해 시간이 오래 걸릴 수 있으므로,
            연결이 수립될 때마다 별도의 작업 스레드를 생성하여 통신을 위임하고,
            메인 스레드는 즉시 다음 클라이언트의 연결을 기다리도록 설계되었다.
            */
            while (true) {
                // accept()는 블로킹 메서드이다.
                // 새로운 클라이언트가 접속하기 전까지 메인 스레드는 여기서 실행을 멈추고 대기한다.
                Socket socket = serverSocket.accept();
                log("클라이언트 연결 성공" + socket.getInetAddress());

                // 실제 통신 로직을 별도의 스레드에서 실행하여,
                // 메인 스레드가 다른 클라이언트의 연결을 처리하는 것을 막지 않도록 한다.
                Task task = new Task(socket);
                Thread thread = new Thread(task);
                thread.start();
            }
        } catch (IOException e) {
            // 서버 소켓 생성 실패 또는 연결 수락 과정에서 심각한 오류 발생 시
            log("서버 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
