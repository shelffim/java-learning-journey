package basic.assignment4.mall;

import basic.assignment4.mall.service.PayService;

public class PayMain {

    public static void main(String[] args) {
        PayService payService = new PayService();

        // 1. 신용카드 결제
        payService.processPay("신용카드", 10000);
        System.out.println("--------------------");

        // 2. 계좌이체 결제
        payService.processPay("계좌이체", 20000);
        System.out.println("--------------------");

        // 3. 모바일 페이 결제 (성공 케이스)
        payService.processPay("모바일", 45000);
        System.out.println("--------------------");

        // 4. 모바일 페이 결제 (실패 케이스)
        payService.processPay("모바일", 60000);
        System.out.println("--------------------");

        // 5. 결제 방식 실패 (실패 케이스)
        payService.processPay("전화", 2000);
    }
}