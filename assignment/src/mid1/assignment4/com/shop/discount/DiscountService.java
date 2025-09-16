package mid1.assignment4.com.shop.discount;

import mid1.assignment4.com.shop.domain.Product;

public class DiscountService {

    public static class PolicyManager {

        private static class FixedAmountDiscountPolicy implements DiscountPolicy {

            private final int fixedDiscountAmount;

            public FixedAmountDiscountPolicy(int fixedDiscountAmount) {
                this.fixedDiscountAmount = fixedDiscountAmount;
            }

            @Override
            public int applyDiscount(int price) {
                return price - fixedDiscountAmount;
            }
        }

        private static class PercentageDiscountPolicy implements DiscountPolicy {

            private final int percentageDiscountRate;

            public PercentageDiscountPolicy(int percentageDiscountRate) {
                this.percentageDiscountRate = percentageDiscountRate;
            }

            @Override
            public int applyDiscount(int price) {
                int discountAmount = price * percentageDiscountRate;
                return price - discountAmount;
            }
        }

        public static DiscountPolicy getFixedAmountPolicy(int fixedDiscountAmount) {
            return new FixedAmountDiscountPolicy(fixedDiscountAmount);
        }

        public static DiscountPolicy getPercentageDiscountPolicy(int percentageDiscountRate) {
            return new PercentageDiscountPolicy(percentageDiscountRate);
        }
    }

    public int applyEventDiscount(Product product) {

        class EventDiscountPolicy implements DiscountPolicy {

            @Override
            public int applyDiscount(int price) {
                int discountAmount = 0;
                if (price >= 10000) {
                    discountAmount = (int) (price * 0.15);
                }

                return price - discountAmount;
            }
        }

        int productPrice = product.getPrice();
        EventDiscountPolicy eventDiscountPolicy = new EventDiscountPolicy();

        return eventDiscountPolicy.applyDiscount(productPrice);
    }
}
