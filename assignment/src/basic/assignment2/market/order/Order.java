package basic.assignment2.market.order;

import basic.assignment2.market.item.Item;

public class Order {
    // 주문의 기본 정보
    private long orderId;      // 주문 고유 ID (식별자)
    private long memberId;     // 주문한 회원의 ID (연관 관계)
    private Item[] items;      // 주문한 상품 목록 (배열 사용)
    
    // 금액 정보
    private int totalPrice;    // 할인 전 총 주문 금액 (원가)
    private int finalPrice;    // 할인 적용 후 최종 결제 금액

    public Order(long orderId, long memberId, Item[] items) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.items = items;
        
        // 생성자에서 자동으로 총 금액 계산 (편의성)
        totalPrice = getCalculateTotalPrice();
        
        // 초기 최종 금액은 할인 전 금액과 동일
        finalPrice = totalPrice;
    }

    /**
     * - 주문 상품 목록을 순회하며 각 상품의 가격을 합산
     */
    private int getCalculateTotalPrice() {
        int sum = 0;
        for (Item item : items) {
            sum += item.calculateTotalPrice();
        }
        return sum;
    }

    /**
     * - OrderService에서 할인 정책 적용 후 최종 금액을 설정하기 위해 필요
     */
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * 할인 전 총 주문 금액 조회
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * 할인 적용 후 최종 결제 금액 조회
     */
    public int getFinalPrice() {
        return finalPrice;
    }
}
