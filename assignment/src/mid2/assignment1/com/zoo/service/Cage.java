package mid2.assignment1.com.zoo.service;

import mid2.assignment1.com.zoo.domain.Animal;

public class Cage <T extends Animal>{
    private T animal;

    public void add(T animal) {
        this.animal = animal;
    }

    public T getAnimal() {
        return this.animal;
    }

    public void makeSound() {
        this.animal.sound();
    }

    public void removeCage() {
        this.animal = null;
    }

}
