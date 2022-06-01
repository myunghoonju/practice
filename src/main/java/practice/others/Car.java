package practice.others;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private String name;
    private String designer;

    public Car(String name, String designer) {
        this.name = name;
        this.designer = designer;
    }
}
