package basic.assignment4.mall.service;

import basic.assignment4.mall.pay.Pay;
import static basic.assignment4.mall.service.PayStore.findPay;

public class PayService {

    public void processPay(String opt, int amount) {
        Pay pay;

        pay = findPay(opt);

        System.out.println("결제를 시작합니다.");
        if (pay.processPay(amount)) {
            System.out.println("결제를 완료했습니다.");
        } else {
            System.out.println("결제를 실패했습니다.");
        }
    }
}
