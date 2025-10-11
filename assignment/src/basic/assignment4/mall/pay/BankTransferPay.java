package basic.assignment4.mall.pay;

/**
 * 계좌이체 결제를 처리하는 클래스
 * 결제 금액의 10%에 해당하는 수수료가 추가로 부과됩니다.
 */
public class BankTransferPay implements Pay
{
    /**
     * 계좌이체로 결제를 처리합니다.
     * 결제 금액에 대한 수수료가 추가로 계산되어 출력됩니다.
     */
    @Override
    public boolean processPay(int amount) {
        System.out.println("계좌이체로 " + amount + "원 결제를 시도합니다.");

        int fee = feePay(amount);
        System.out.println("수수료 " + fee + "원이 추가됩니다.");
        return true;
    }

    /**
     * 계좌이체 수수료를 계산합니다.
     */
    private int feePay(int amount) {
        return amount / 10;
    }
}
