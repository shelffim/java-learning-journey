package basic.assignment2.market.policy;

import basic.assignment2.market.member.Grade;
import basic.assignment2.market.member.Member;

public class DiscountPolicy {

    public int discount(Member member, int price) {
        int amount = 0;
        if (member.getGrade() == Grade.VIP) {
            amount = price / 10;
        }
        return price - amount;
    }
}
