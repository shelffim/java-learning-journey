package adv1.assignment2.com.inventory.domain;

public class Inventory {
    private int stock;

    public Inventory(int stock) {
        this.stock = stock;
    }

    public void restock(int quantity) {
        int currentStock;
        synchronized (this) {
            this.stock += quantity;
            currentStock = this.stock;
        }
        System.out.println("[" + Thread.currentThread().getName() + "] >> 상품 " + quantity + "개 입고. [현재 재고: " + currentStock + "]");
    }

    public void ship(int quantity) {
        int currentStock;
        synchronized (this) {
            this.stock -= quantity;
            currentStock = this.stock;
        }
        System.out.println("[" + Thread.currentThread().getName() + "] >> 상품 " + quantity + "개 판매. [현재 재고: " + currentStock + "]");
    }

    public synchronized int checkStock() {
        return this.stock;
    }
}
