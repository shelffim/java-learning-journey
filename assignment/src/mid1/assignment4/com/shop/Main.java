package mid1.assignment4.com.shop;

import mid1.assignment4.com.shop.discount.DiscountPolicy;
import mid1.assignment4.com.shop.discount.DiscountService;
import mid1.assignment4.com.shop.domain.Product;
import mid1.assignment4.com.shop.domain.User;

public class Main {

    public static void main(String[] args) {
        // 1. 상품 생성
        Product product = new Product("노트북", 1000000);
        System.out.println("원본 상품 가격: " + product.getPrice() + "원");
        System.out.println("--------------------");

        // 2. [Static 중첩 클래스] 고정 금액 할인 적용
        DiscountPolicy fixedAmountPolicy = DiscountService.PolicyManager.getFixedAmountPolicy(10000);
        int discountedPrice1 = fixedAmountPolicy.applyDiscount(product.getPrice());
        System.out.println("고정 금액 할인 적용 후: " + discountedPrice1 + "원");
        System.out.println("--------------------");

        // 3. [Local 클래스] 기간 한정 이벤트 할인 적용
        DiscountService discountService = new DiscountService();
        int discountedPrice2 = discountService.applyEventDiscount(product);
        System.out.println("이벤트 할인 적용 후: " + discountedPrice2 + "원");
        System.out.println("--------------------");

        // 4. [Inner 클래스] 사용자 쿠폰 할인 적용
        User user = new User("홍길동");
        DiscountPolicy coupon = user.new Coupon(2000); // User 객체를 통해서만 생성 가능
        int discountedPrice3 = coupon.applyDiscount(product.getPrice());
        System.out.println(user.getName() + "님 쿠폰 적용 후: " + discountedPrice3 + "원");
        System.out.println("--------------------");

        // 5. [Anonymous 클래스] 반짝 타임 세일 적용
        System.out.println("!! 반짝 타임 세일 시작 !!");
        DiscountPolicy flashSalePolicy = new DiscountPolicy() {
            @Override
            public int applyDiscount(int price) {
                return price / 2;
            }
        };
        int discountedPrice4 = flashSalePolicy.applyDiscount(product.getPrice());
        System.out.println("타임 세일 적용 후: " + discountedPrice4 + "원");
    }
}
