package basic.assignment4.mall.pay;

/**
 * 결제 방식을 정의하는 인터페이스
 * 다양한 결제 수단(신용카드, 계좌이체, 모바일 등)을 통합적으로 처리하기 위한 다형성 구현
 */
public interface Pay {
    boolean processPay(int amount);
}
