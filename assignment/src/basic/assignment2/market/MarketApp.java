package basic.assignment2.market;

import basic.assignment2.market.item.Item;
import basic.assignment2.market.member.Grade;
import basic.assignment2.market.member.Member;
import basic.assignment2.market.order.Order;
import basic.assignment2.market.order.OrderService;

public class MarketApp {

    public static void main(String[] args) {
        // OrderService는 public이므로 다른 패키지에서도 생성 가능
        OrderService orderService = new OrderService();

        // Member, Item 등도 public 클래스이므로 생성 가능
        Member member1 = new Member(1L, "김기본", Grade.BASIC);
        Member member2 = new Member(2L, "박브압", Grade.VIP);

        Item itemA = new Item("노트북", 1000000, 10);
        Item itemB = new Item("마우스", 50000, 20);
        Item itemC = new Item("키보드", 100000, 15);

        System.out.println("--- 초기 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println(itemC.getItemName() + " 재고: " + itemC.getStockQuantity());
        System.out.println("======================================\n");

        // [시나리오 1] VIP 회원 주문
        System.out.println("--- [시나리오 1] VIP 회원 주문 ---");
        Item[] vipOrderItems = {itemA, itemB, itemB};
        Order order1 = orderService.createOrder(member2, vipOrderItems);

        System.out.println("주문이 완료되었습니다.");
        System.out.println("총 주문 금액: " + order1.getTotalPrice() + "원");
        System.out.println("할인 적용된 최종 결제 금액: " + order1.getFinalPrice() + "원");

        System.out.println("\n--- 주문 후 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println("======================================\n");
    }
}
