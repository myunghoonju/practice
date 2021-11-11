package practice.reflection;

public class Goat extends Animal implements Locomotion {

    public Goat(String name) {
        super(name);
    }

    @Override
    public String eats() {
        return "grass";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }

    @Override
    public String getSound() {
        return "bleat";
    }
}
