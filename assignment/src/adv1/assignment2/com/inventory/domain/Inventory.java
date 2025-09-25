package adv1.assignment2.com.inventory.domain;

public class Inventory {
    private volatile int stock;

    public Inventory(int stock) {
        this.stock = stock;
    }

    public synchronized void restock(int quantity) {
        this.stock += quantity;
        System.out.println("[" + Thread.currentThread().getName() + "] >> 상품 " + quantity + "개 입고. [현재 재고: " + this.stock + "]");
    }

    public synchronized void ship(int quantity) {
        this.stock -= quantity;
        System.out.println("[" + Thread.currentThread().getName() + "] >> 상품 " + quantity + "개 판매. [현재 재고: " + this.stock + "]");
    }

    public synchronized int checkStock() {
        return this.stock;
    }
}
