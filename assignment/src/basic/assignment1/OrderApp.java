package basic.assignment1;

public class OrderApp {
    public static void main(String[] args) {
        // 1. 주문 서비스를 생성합니다.
        // OrderService 클래스의 인스턴스를 직접 생성합니다.
        OrderService orderService = new OrderService();

        // 2. 회원 정보를 생성합니다.
        Member member1 = new Member(1L, "김기본", Grade.BASIC);
        Member member2 = new Member(2L, "박브압", Grade.VIP);

        // 3. 상품 정보를 생성합니다.
        Item itemA = new Item("노트북", 1000000, 10);
        Item itemB = new Item("마우스", 50000, 20);
        Item itemC = new Item("키보드", 100000, 15);

        System.out.println("--- 초기 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println(itemC.getItemName() + " 재고: " + itemC.getStockQuantity());
        System.out.println("======================================\n");


        // 4. [시나리오 1] VIP 회원이 노트북 1개, 마우스 2개를 주문합니다.
        System.out.println("--- [시나리오 1] VIP 회원 주문 ---");
        Item[] vipOrderItems = {itemA, itemB, itemB};
        Order order1 = orderService.createOrder(member2, vipOrderItems);

        // 주문 결과 출력
        if (order1 != null) {
            System.out.println("주문이 완료되었습니다.");
            System.out.println("주문자: " + member2.getName());
            System.out.println("총 주문 금액: " + order1.getTotalPrice() + "원");
            System.out.println("할인 적용된 최종 결제 금액: " + order1.getFinalPrice() + "원");
        }
        System.out.println("\n--- 주문 후 재고 상태 ---");
        System.out.println(itemA.getItemName() + " 재고: " + itemA.getStockQuantity());
        System.out.println(itemB.getItemName() + " 재고: " + itemB.getStockQuantity());
        System.out.println("======================================\n");


        // 5. [시나리오 2] BASIC 회원이 키보드 3개를 주문합니다.
        System.out.println("--- [시나리오 2] BASIC 회원 주문 ---");
        Item[] basicOrderItems = {itemC, itemC, itemC};
        Order order2 = orderService.createOrder(member1, basicOrderItems);

        // 주문 결과 출력
        if (order2 != null) {
            System.out.println("주문이 완료되었습니다.");
            System.out.println("주문자: " + member1.getName());
            System.out.println("총 주문 금액: " + order2.getTotalPrice() + "원");
            System.out.println("할인 적용된 최종 결제 금액: " + order2.getFinalPrice() + "원");
        }
        System.out.println("\n--- 주문 후 재고 상태 ---");
        System.out.println(itemC.getItemName() + " 재고: " + itemC.getStockQuantity());
        System.out.println("======================================\n");
    }
}
