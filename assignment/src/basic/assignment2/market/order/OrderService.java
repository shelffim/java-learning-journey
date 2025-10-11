package basic.assignment2.market.order;

import basic.assignment2.market.item.Item;
import basic.assignment2.market.member.Member;
import basic.assignment2.market.policy.DiscountPolicy;

public class OrderService {
    // 주문 ID 자동 생성을 위한 시퀀스 (0부터 시작하여 자동 증가)
    private long orderId = 0L;

    // 할인 정책 객체 - 회원 등급에 따른 할인 적용 담당
    // private으로 선언하여 외부에서 직접 접근 불가 (캡슐화)
    private DiscountPolicy policy = new DiscountPolicy();

    public Order createOrder(Member member, Item[] items) {
        // 1. 주문 객체 생성 (orderId++ : 후위 증가 연산자로 현재 값 사용 후 증가)
        Order order = new Order(orderId++, member.getMemerId(), items);

        // 2. 할인 정책 적용 - 회원 등급과 총 금액을 기반으로 최종 금액 계산
        //    policy.discount()는 회원 등급(VIP 10% 할인)에 따라 할인된 금액 반환
        int finalPrice = policy.discount(member, order.getTotalPrice());
        
        // 3. 계산된 최종 금액을 주문 객체에 설정
        order.setFinalPrice(finalPrice);

        // 4. 재고 자동 차감 - 주문한 모든 상품의 재고를 1개씩 감소
        for (Item item : items) {
            item.removeStock(1);  // 각 상품당 1개씩 재고 차감
        }

        // 5. 완성된 주문 객체 반환
        return order;
    }
}
