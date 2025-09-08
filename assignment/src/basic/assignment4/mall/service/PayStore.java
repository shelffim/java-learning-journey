package basic.assignment4.mall.service;

import basic.assignment4.mall.pay.*;

public abstract class PayStore {

    public static Pay findPay(String opt) {
        if (opt.equals("신용카드")) {
            return new CreditCardPay();
        } else if (opt.equals("계좌이체")) {
            return new BankTransferPay();
        } else if (opt.equals("모바일")) {
            return new MobilePay();
        }

        return new FailPay();
    }
}
