package basic.assignment4.mall.pay;

public class MobilePay implements Pay
{
    @Override
    public boolean processPay(int amount) {
        System.out.println("모바일 페이로 " + amount + "원 결제를 시도합니다.");
        return isBelow(amount);
    }

    // 50000원 미만일 경우에만 결제 가능
    private boolean isBelow(int amount) {
        if (amount < 50000) {
            System.out.println("결제가 성공했습니다.");
            return true;
        }

        System.out.println("결제 한도를 초과했습니다.");
        return false;
    }
}
