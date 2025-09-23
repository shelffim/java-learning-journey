package adv1.assignment1.com.practical.threads;

import adv1.assignment1.com.practical.threads.task.BackupTask;
import adv1.assignment1.com.practical.threads.task.DownloadTask;

public class Main {

    public static void main(String[] args) {
        System.out.println("메인 스레드 시작");

        // 작업 생성
        Runnable backupTask = new BackupTask(3000);
        Runnable downloadTask = new DownloadTask(5000);

        // 스레드 생성 및 작업 할당
        Thread backupThread = new Thread(backupTask, "BackupThread");
        Thread downloadThread = new Thread(downloadTask, "DownloadThread");

        // 스레드 시작
        backupThread.start();
        downloadThread.start();

        // 모든 작업이 끝날 때까지 기다림
        try {
            backupThread.join();
            System.out.println("✅ " + backupThread.getName() + " 작업 완료." + Thread.currentThread().getName() + " 가 확인했습니다.");
            downloadThread.join();
            System.out.println("✅ " + downloadThread.getName() + " 작업 완료." + Thread.currentThread().getName() + " 가 확인했습니다.");
        } catch (InterruptedException e) {
            System.out.println("[Exception] " + Thread.currentThread().getName() + " is interrupted.");
            // 현재 스레드의 interrupt 상태를 다시 true로 설정합니다.
            Thread.currentThread().interrupt();
        }

        System.out.println("🎉 모든 작업이 완료되었습니다.");
        System.out.println("메인 스레드 종료");
    }
}
