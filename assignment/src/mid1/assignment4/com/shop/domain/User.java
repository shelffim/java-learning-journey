package mid1.assignment4.com.shop.domain;

import mid1.assignment4.com.shop.discount.DiscountPolicy;

public class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public class Coupon implements DiscountPolicy {
        private static int discountAmount;

        public Coupon(int discountAmount) {
            this.discountAmount = discountAmount;
        }

        @Override
        public int applyDiscount(int price) {
            return price - discountAmount;
        }
    }
}
