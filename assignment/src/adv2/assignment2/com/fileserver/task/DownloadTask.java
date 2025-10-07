package adv2.assignment2.com.fileserver.task;

import java.io.*;
import java.net.Socket;

public class DownloadTask implements Runnable{

    private final Socket socket;
    private final int taskId;
    private static final String TARGET = "test.zip";

    public DownloadTask(Socket socket, int taskId) {
        this.socket = socket;
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try (Socket clientSocket = this.socket;
             DataInputStream input = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            // 서버에 다운로드 요청
            output.writeUTF("download");
            output.writeUTF(TARGET);

            // 서버는 항상 응답의 첫 부분으로 성공 여부를 보내기로 약속되어 있다.
            boolean isSuccess = input.readBoolean();

            if (isSuccess) {
                long fileSize = input.readLong();
                System.out.println(TARGET + " 파일 다운로드 시작");

                // 다운로드 받을 로컬 파일 정한다.
                File downloadFile = new File("adv2/download_" + TARGET);

                try (FileOutputStream fos = new FileOutputStream(downloadFile);
                     BufferedOutputStream bos = new BufferedOutputStream(fos);
                ) {
                    // 데이터를 묶어서 송수신하기 위한 버킷을 추가한다.
                    final int BUFFER_SIZE = 8192;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead;
                    long totalSize = 0;

                    long start = System.currentTimeMillis();

                    while (totalSize < fileSize && (bytesRead = input.read(buffer)) != -1){
                        bos.write(buffer, 0, bytesRead);
                        totalSize += bytesRead;
                    }

                    long end = System.currentTimeMillis();
                    System.out.println("Task " + taskId + ": 다운로드 완료. (소요 시간: " + (end - start) / 1000.0 + "초)");

                }
            } else {
                System.out.println("Task " + taskId + ": 다운로드 실패 - " + input.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
