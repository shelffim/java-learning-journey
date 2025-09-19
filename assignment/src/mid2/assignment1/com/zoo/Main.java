package mid2.assignment1.com.zoo;

import mid2.assignment1.com.zoo.domain.Animal;
import mid2.assignment1.com.zoo.domain.Cat;
import mid2.assignment1.com.zoo.domain.Dog;
import mid2.assignment1.com.zoo.service.Cage;
import mid2.assignment1.com.zoo.service.ZooManager;

public class Main {

    public static void main(String[] args) {
        // 1. 제네릭 클래스 Cage 생성
        Cage<Dog> dogCage = new Cage<>();
        dogCage.add(new Dog("바둑이"));

        Cage<Cat> catCage = new Cage<>();
        catCage.add(new Cat("나비"));

        // Cage<String> stringCage = new Cage<>(); // T extends Animal 제약으로 컴파일 오류 발생

        // 2. 제네릭 메서드 printAnimalName 테스트
        System.out.println("== 제네릭 메서드 테스트 ==");
        ZooManager.printAnimalName(dogCage);
        ZooManager.printAnimalName(catCage);
        System.out.println("--------------------");

        // 3. 와일드카드 moveAnimal 테스트
        // Animal 타입의 우리를 새로 만듦 (모든 동물을 받을 수 있음)
        Cage<Animal> animalCage = new Cage<>();

        System.out.println("== 와일드카드 테스트: 개를 옮기기 전 ==");
        System.out.println("개 우리: " + dogCage.getAnimal());
        System.out.println("동물 우리: " + animalCage.getAnimal());
        System.out.println("--------------------");

        // 개 우리(from)에 있던 개를 동물 우리(to)로 옮긴다.
        ZooManager.moveAnimal(dogCage, animalCage);

        System.out.println("== 와일드카드 테스트: 개를 옮긴 후 ==");
        System.out.println("개 우리: " + dogCage.getAnimal()); // 개 우리에는 이제 동물이 없음
        System.out.println("동물 우리: " + animalCage.getAnimal());
    }
}
