package adv2.assignment2.com.fileserver.client;

import adv2.assignment2.com.fileserver.task.DownloadTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PerformanceTester {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 동시에 접속할 클라이언트 수
        int numberOfClients = 10;

        // 동시 실행을 관리할 스레드 풀 생성
        try (ExecutorService executor = Executors.newFixedThreadPool(numberOfClients)) {

            System.out.println(numberOfClients + "개의 클라이언트가 동시에 다운로드를 진행합니다....");
            long start = System.currentTimeMillis();

            // 클라이언트 수만큼 다운로드 작업을 스레드 풀에 제출
            for (int i = 0; i < numberOfClients; i++) {
                executor.submit(new DownloadTask(new Socket(HOST, PORT), i));
            }

            // 모든 작업이 끝날 때까지 대기
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES); // 최대 1분까지 기다림

            long end = System.currentTimeMillis();
            System.out.println("--- 모든 다운로드 작업 완료 ---");
            System.out.println("총 소요 시간: " + (end - start) / 1000.0 + "초");
        }

    }
}
