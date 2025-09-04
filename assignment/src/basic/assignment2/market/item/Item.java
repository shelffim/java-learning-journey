package basic.assignment2.market.item;

public class Item {
    private String itemName;
    private int price;
    private int stockQuantity;

    public Item(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            System.out.println("에러: 재고가 부족합니다.");
            return;
        }
        this.stockQuantity = restStock;
    }

    public int calculateTotalPrice() {
        return price * stockQuantity;
    }

    public int getPrice() {
        return price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
