package basic.assignment4.mall.pay;

public class CreditCardPay implements Pay
{
    @Override
    public boolean processPay(int amount) {
        System.out.println("신용카드로 " + amount + "원 결제를 시도합니다.");
        return true;
    }
}
