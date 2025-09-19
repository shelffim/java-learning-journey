package mid2.assignment1.com.zoo.domain;

public abstract class Animal {
    private final String name;

    protected Animal(String name) {
        this.name = name;
    }

    public abstract void sound();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
