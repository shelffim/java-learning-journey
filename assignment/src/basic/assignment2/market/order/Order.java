package basic.assignment2.market.order;

import basic.assignment2.market.item.Item;

public class Order {
    private long orderId;
    private long memberId;
    private Item[] items;
    private int totalPrice;
    private int finalPrice;

    public Order(long orderId, long memberId, Item[] items) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.items = items;
        totalPrice = getCalculateTotalPrice();
        finalPrice = totalPrice;
    }

    private int getCalculateTotalPrice() {
        int sum = 0;
        for (Item item : items) {
            sum += item.calculateTotalPrice();
        }
        return sum;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
}
