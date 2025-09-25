package adv1.assignment2.com.inventory.task;

import adv1.assignment2.com.inventory.domain.Inventory;

public class Consumer implements Runnable {

    private Inventory inventory;

    public Consumer(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            inventory.ship(10);
        }
    }
}
