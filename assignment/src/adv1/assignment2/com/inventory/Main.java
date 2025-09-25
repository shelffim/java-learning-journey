package adv1.assignment2.com.inventory;

import adv1.assignment2.com.inventory.domain.Inventory;
import adv1.assignment2.com.inventory.task.Consumer;
import adv1.assignment2.com.inventory.task.Producer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(1000); // 초기 재고 1000개

        // 입고 담당자 2명, 판매자 3명 스레드 생성
        Thread producer1 = new Thread(new Producer(inventory), "Producer-1");
        Thread producer2 = new Thread(new Producer(inventory), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(inventory), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(inventory), "Consumer-2");
        Thread consumer3 = new Thread(new Consumer(inventory), "Consumer-3");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        // 모든 스레드가 작업을 마칠 때까지 대기
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();
        consumer3.join();

        System.out.println("------------------------------------");
        System.out.println("모든 입고 및 판매 작업 완료.");
        System.out.println("최종 재고: " + inventory.checkStock());
    }
}
