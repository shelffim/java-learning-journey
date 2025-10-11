package basic.assignment2.market.item;

public class Item {
    // private으로 선언하여 외부에서 직접 접근 불가 (캡슐화 원칙)
    private String itemName;      // 상품명
    private int price;             // 개당 가격
    private int stockQuantity;     // 현재 재고 수량

    public Item(String itemName, int price, int stockQuantity) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * - 주문 시 자동으로 재고를 감소시키기 위한 비즈니스 로직
     * - 재고 부족 시 에러 처리를 포함하여 데이터 무결성 보장
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        
        // 재고 부족 검증 - 음수 재고 방지 (비즈니스 규칙)
        if (restStock < 0) {
            System.out.println("에러: 재고가 부족합니다.");
            return;
        }
        
        this.stockQuantity = restStock;
    }

    /**
     * - 주문 시 여러 상품의 총 금액을 계산하기 위한 메서드
     * - (개당 가격 * 재고 수량)으로 계산
     */
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
