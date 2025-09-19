package mid2.assignment1.com.zoo.domain;

public class Cat extends Animal{

    public Cat(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println("야옹");
    }
}
