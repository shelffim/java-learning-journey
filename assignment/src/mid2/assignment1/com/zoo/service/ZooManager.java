package mid2.assignment1.com.zoo.service;

import mid2.assignment1.com.zoo.domain.Animal;

public class ZooManager {

    public static <T extends Animal> void printAnimalName(Cage<T> cage) {
        String name = cage.getAnimal().getName();

        System.out.println("동물 이름: " + name);

    }

    public static void moveAnimal(Cage <? extends Animal> fromCage, Cage <? super Animal> toCage) {
        Animal fromCageAnimal = fromCage.getAnimal();

        toCage.add(fromCageAnimal);

        fromCage.removeCage();
    }

}
