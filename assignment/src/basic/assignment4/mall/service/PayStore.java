package basic.assignment4.mall.service;

import basic.assignment4.mall.pay.*;

/**
 * 결제 방식을 생성하고 관리하는 팩토리 클래스
 * 결제 수단 이름을 기반으로 적절한 결제 구현체를 반환합니다.
 * Simple Factory 패턴을 적용하여 객체 생성 로직을 중앙 관리합니다.
 */
public abstract class PayStore {

    /**
     * 결제 방식 이름에 따라 적절한 결제 구현체를 찾아 반환합니다.
     * 지원하지 않는 결제 방식이 입력되면 FailPay 객체를 반환합니다.
     */
    public static Pay findPay(String opt) {
        if (opt.equals("신용카드")) {
            return new CreditCardPay();
        } else if (opt.equals("계좌이체")) {
            return new BankTransferPay();
        } else if (opt.equals("모바일")) {
            return new MobilePay();
        }

        // 지원하지 않는 결제 방식일 경우 Null Object 패턴으로 처리
        return new FailPay();
    }
}
