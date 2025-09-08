package basic.assignment4.mall.pay;

public class FailPay implements Pay
{
    @Override
    public boolean processPay(int amount) {
        System.out.println("결제방법이 틀렸습니다. 다시 시도하세요.");
        return false;
    }
}
