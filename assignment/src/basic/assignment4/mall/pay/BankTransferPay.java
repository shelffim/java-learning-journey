package basic.assignment4.mall.pay;

public class BankTransferPay implements Pay
{
    @Override
    public boolean processPay(int amount) {
        System.out.println("계좌이체로 " + amount + "원 결제를 시도합니다.");

        int fee = feePay(amount);
        System.out.println("수수료 " + fee + "원이 추가됩니다.");
        return true;
    }

    private int feePay(int amount) {
        return amount / 10;
    }
}
