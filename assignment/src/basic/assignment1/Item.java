package basic.assignment1;

public class Item {
    // 이전 답변과 동일
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

    public String getItemName() { return itemName; }
    public int getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
}
