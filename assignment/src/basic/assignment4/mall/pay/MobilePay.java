package basic.assignment4.mall.pay;

/**
 * 모바일 결제를 처리하는 클래스
 * 50,000원 미만의 금액만 결제 가능하며, 한도를 초과하면 결제가 실패합니다.
 */
public class MobilePay implements Pay
{
    private static final int PAYMENT_LIMIT = 50000;  // 모바일 결제 한도
    
    /**
     * 모바일 페이로 결제를 처리합니다.
     * 결제 한도를 확인하여 결제 가능 여부를 판단합니다.
     * 
     * @param amount 결제 금액
     * @return 결제 성공 여부 (50,000원 미만이면 true, 이상이면 false)
     */
    @Override
    public boolean processPay(int amount) {
        System.out.println("모바일 페이로 " + amount + "원 결제를 시도합니다.");
        return isBelow(amount);
    }

    /**
     * 결제 금액이 한도 이내인지 확인합니다.
     * 
     * @param amount 결제 금액
     * @return 결제 한도(50,000원) 미만일 경우 true, 이상일 경우 false
     */
    private boolean isBelow(int amount) {
        if (amount < PAYMENT_LIMIT) {
            System.out.println("결제가 성공했습니다.");
            return true;
        }

        System.out.println("결제 한도를 초과했습니다.");
        return false;
    }
}
