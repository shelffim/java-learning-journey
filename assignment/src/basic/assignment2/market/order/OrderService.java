package basic.assignment2.market.order;

import basic.assignment2.market.item.Item;
import basic.assignment2.market.member.Member;
import basic.assignment2.market.policy.DiscountPolicy;

public class OrderService {
    private long orderId = 0L;

    private DiscountPolicy policy = new DiscountPolicy();

    public Order createOrder(Member member, Item[] items) {
        Order order = new Order(orderId++, member.getMemerId(), items);

        int finalPrice = policy.discount(member, order.getTotalPrice());
        order.setFinalPrice(finalPrice);

        // 재고 감소
        for (Item item : items) {
            item.removeStock(1);
        }

        return order;
    }
}
