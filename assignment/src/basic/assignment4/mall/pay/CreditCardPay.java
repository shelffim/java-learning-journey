package basic.assignment4.mall.pay;

/**
 * 신용카드 결제를 처리하는 클래스
 * 별도의 수수료나 결제 한도 없이 모든 금액에 대해 결제를 승인합니다.
 */
public class CreditCardPay implements Pay
{
    /**
     * 신용카드로 결제를 처리합니다.
     */
    @Override
    public boolean processPay(int amount) {
        System.out.println("신용카드로 " + amount + "원 결제를 시도합니다.");
        return true;
    }
}
