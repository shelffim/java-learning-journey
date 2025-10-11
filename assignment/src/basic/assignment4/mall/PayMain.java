package basic.assignment4.mall;

import basic.assignment4.mall.service.PayService;

/**
 * 결제 시스템의 메인 실행 클래스
 * 다양한 결제 방식과 시나리오를 테스트합니다.
 * 
 * 시스템 구조:
 * - Pay 인터페이스: 결제 수단 추상화
 * - 구현체: CreditCardPay, BankTransferPay, MobilePay, FailPay
 * - PayStore: 결제 수단 생성 팩토리 (Simple Factory 패턴)
 * - PayService: 결제 프로세스 처리 (전략 패턴)
 */
public class PayMain {

    public static void main(String[] args) {
        PayService payService = new PayService();

        // 1. 신용카드 결제 - 수수료 없음, 한도 제한 없음
        payService.processPay("신용카드", 10000);
        System.out.println("--------------------");

        // 2. 계좌이체 결제 - 10% 수수료 부과
        payService.processPay("계좌이체", 20000);
        System.out.println("--------------------");

        // 3. 모바일 페이 결제 (성공 케이스) - 50,000원 미만
        payService.processPay("모바일", 45000);
        System.out.println("--------------------");

        // 4. 모바일 페이 결제 (실패 케이스) - 50,000원 이상 한도 초과
        payService.processPay("모바일", 60000);
        System.out.println("--------------------");

        // 5. 잘못된 결제 방식 (실패 케이스) - Null Object 패턴으로 처리
        payService.processPay("전화", 2000);
    }
}