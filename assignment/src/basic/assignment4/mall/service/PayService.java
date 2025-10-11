package basic.assignment4.mall.service;

import basic.assignment4.mall.pay.Pay;
import static basic.assignment4.mall.service.PayStore.findPay;

/**
 * 결제 프로세스를 총괄하는 서비스 클래스
 * 결제 수단을 선택하고 결제를 실행한 후 결과를 처리합니다.
 */
public class PayService {

    /**
     * 지정된 결제 방식과 금액으로 결제를 처리합니다.
     * 전략 패턴(Strategy Pattern)을 사용하여 다양한 결제 방식을 동적으로 처리합니다.
     */
    public void processPay(String opt, int amount) {
        // PayStore를 통해 적절한 결제 구현체를 조회
        Pay pay = findPay(opt);

        System.out.println("결제를 시작합니다.");
        
        // 다형성을 활용하여 각 결제 수단별로 다른 로직 실행
        if (pay.processPay(amount)) {
            System.out.println("결제를 완료했습니다.");
        } else {
            System.out.println("결제를 실패했습니다.");
        }
    }
}
