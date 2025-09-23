package adv1.assignment1.com.practical.threads.task;

public class BackupTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("데이터 백업 시작...");
            Thread.sleep(3000);
            System.out.println("데이터 백업 완료. (소요 시간: 3초)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
