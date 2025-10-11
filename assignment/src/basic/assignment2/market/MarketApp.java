package basic.assignment2.market;

import basic.assignment2.market.item.Item;
import basic.assignment2.market.member.Grade;
import basic.assignment2.market.member.Member;
import basic.assignment2.market.order.Order;
import basic.assignment2.market.order.OrderService;

public class MarketApp {

    public static void main(String[] args) {
        // [1단계] 주문 서비스 생성
        OrderService orderService = new OrderService();

        // [2단계] 테스트 데이터 준비 - 회원 생성
        Member member1 = new Member(1L, "김기본", Grade.BASIC);  // 일반 회원
        Member member2 = new Member(2L, "박브압", Grade.VIP);     // VIP 회원

        // [3단계] 테스트 데이터 준비 - 상품 생성
        Item itemA = new Item("노트북", 1000000, 10);
        Item itemB = new Item("마우스", 50000, 20);
        Item itemC = new Item("키보드", 100000, 15);

        // [4단계] 초기 재고 상태 출력
        // 주문 전 재고를 확인하여 주문 후 재고 감소를 확인할 수 있게 함
        System.out.println("--- 초기 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println(itemC.getItemName() + " 재고: " + itemC.getStockQuantity());
        System.out.println("======================================\n");

        // [5단계] 시나리오 1 - VIP 회원 주문
        // VIP 회원의 주문을 통해 10% 할인 정책이 정상 작동하는지 확인
        System.out.println("--- [시나리오 1] VIP 회원 주문 ---");
        
        // 주문 상품 목록 생성 (배열 사용)
        // 노트북 1개, 마우스 2개 주문
        // 총액: 1,000,000 + 50,000 + 50,000 = 1,100,000원
        // VIP 할인 10% 적용 → 최종: 990,000원
        Item[] vipOrderItems = {itemA, itemB, itemB};
        
        // OrderService의 createOrder() 호출
        // 자동으로 할인 적용 + 재고 차감이 수행됨
        Order order1 = orderService.createOrder(member2, vipOrderItems);

        // [6단계] 주문 결과 출력
        // 할인 전 금액과 할인 후 금액을 비교하여 할인 정책 확인
        System.out.println("주문이 완료되었습니다.");
        System.out.println("총 주문 금액: " + order1.getTotalPrice() + "원");
        System.out.println("할인 적용된 최종 결제 금액: " + order1.getFinalPrice() + "원");

        // [7단계] 주문 후 재고 상태 확인
        // removeStock()이 정상 동작하여 재고가 감소했는지 확인
        // 노트북: 10 → 9, 마우스: 20 → 18 (2개 주문)
        System.out.println("\n--- 주문 후 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println("======================================\n");
    }
}
