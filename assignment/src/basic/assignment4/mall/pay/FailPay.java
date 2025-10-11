package basic.assignment4.mall.pay;

/**
 * 잘못된 결제 방식이 선택되었을 때 사용되는 Null Object 패턴 구현 클래스
 * 지원하지 않는 결제 수단이 입력되었을 때 예외 대신 실패를 처리합니다.
 */
public class FailPay implements Pay
{
    /**
     * 잘못된 결제 방식에 대한 실패 처리를 수행합니다.
     */
    @Override
    public boolean processPay(int amount) {
        System.out.println("결제방법이 틀렸습니다. 다시 시도하세요.");
        return false;
    }
}
