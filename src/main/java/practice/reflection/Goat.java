package practice.reflection;

public class Goat extends Animal implements Eating {

    public Goat(String name) {
        super(name);
    }

    @Override
    public String eats() {
        return null;
    }
}
