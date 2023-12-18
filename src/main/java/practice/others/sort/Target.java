package practice.others.sort;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Target {

    private String name;
    private long salary;

    public Target(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    public static int compareSalary(Target left, Target right) {
        return Long.compare(left.getSalary(), right.getSalary());
    }

    public static int alphabetic(Target left, Target right) {
        return left.getName().compareTo(right.getName());
    }
}
