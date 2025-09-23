package adv1.assignment1.com.practical.threads.task;

public class DownloadTask implements Runnable {
    private final int DOWNLOAD_DURATION_MS;

    public DownloadTask(int DOWNLOAD_DURATION_MS) {
        this.DOWNLOAD_DURATION_MS = DOWNLOAD_DURATION_MS;
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] >> 파일 다운로드 시작...");
            Thread.sleep(DOWNLOAD_DURATION_MS);
            System.out.println("[" + Thread.currentThread().getName() + "] >> 파일 다운로드 완료. (소요 시간: 5초)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
