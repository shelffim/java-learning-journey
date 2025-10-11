package basic.assignment2.market.policy;

import basic.assignment2.market.member.Grade;
import basic.assignment2.market.member.Member;

public class DiscountPolicy {

    /**
     * - VIP 회원: 10% 할인 (price / 10)
     * - BASIC 회원: 할인 없음 (amount = 0)
     */
    public int discount(Member member, int price) {
        int amount = 0;  // 할인 금액 초기화
        
        // VIP 회원인 경우에만 10% 할인 적용
        if (member.getGrade() == Grade.VIP) {
            amount = price / 10;  // 10% 할인 (소수점 버림)
        }
        // BASIC 회원은 amount = 0 그대로 유지 (할인 없음)
        
        // 최종 금액 = 원가 - 할인 금액
        return price - amount;
    }
}
