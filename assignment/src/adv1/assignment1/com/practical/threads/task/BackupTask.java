package adv1.assignment1.com.practical.threads.task;

public class BackupTask implements Runnable {
    private final int BACKUP_DURATION_MS;

    public BackupTask(int BACKUP_DURATION_MS) {
        this.BACKUP_DURATION_MS = BACKUP_DURATION_MS;
    }

    @Override
    public void run() {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] >> 데이터 백업 시작...");
            Thread.sleep(BACKUP_DURATION_MS);
            System.out.println("[" + Thread.currentThread().getName() + "] >> 데이터 백업 완료. (소요 시간: 3초)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
