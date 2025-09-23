package adv1.assignment1.com.practical.threads.task;

public class DownloadTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("파일 다운로드 시작...");
            Thread.sleep(5000);
            System.out.println("파일 다운로드 완료. (소요 시간: 5초)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
