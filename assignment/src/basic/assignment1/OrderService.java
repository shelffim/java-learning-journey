package basic.assignment1;

public class OrderService {
    private long orderSequence = 0L;

    public Order createOrder(Member member, Item[] items) {
        // 1. 주문 생성
        Order order = new Order(++orderSequence, member.getMemberId(), items);

        // 2. 할인 적용 로직 (VIP 등급 10% 할인)
        int totalPrice = order.calculateTotalPrice();
        int discountAmount = 0;
        if (member.getGrade() == Grade.VIP) {
            discountAmount = totalPrice / 10;
        }
        order.setFinalPrice(totalPrice - discountAmount);

        // 3. 재고 감소 처리
        for (Item item : items) {
            // 이번 과제에서는 아이템 1개당 주문 수량이 1개라고 가정합니다.
            item.removeStock(1);
        }

        return order;
    }
}
