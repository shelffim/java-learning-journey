package basic.assignment1;

public class Order {
    // 최종 가격을 설정할 수 있는 setter를 추가했습니다.
    private long orderId;
    private long memberId;
    private Item[] items;
    private int totalPrice;
    private int finalPrice;

    public Order(long orderId, long memberId, Item[] items) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.items = items;
        this.totalPrice = calculateTotalPrice();
        this.finalPrice = this.totalPrice; // 할인이 없을 경우를 대비해 초기화
    }

    // for문을 사용해 주문 상품의 가격 총합을 구합니다.
    public int calculateTotalPrice() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    // 할인 정책 적용 한 최종 금액을 저장합니다.
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getTotalPrice() { return totalPrice; }
    public int getFinalPrice() { return finalPrice; }
}
