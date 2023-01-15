package practice.others.copy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeepCopy {

    public static void main(String[] args) {
        Car originCar = new Car("orgin", "me");
        // Easy way of deep copy:: create constructor
        Car copiedCar = new Car(originCar.getName(), originCar.getDesigner());
        log.info("before set origin car name:: {}", originCar.getName());
        originCar.setName("new");
        log.info("after set origin car name:: {}", originCar.getName());
        log.info("copied car name:: {}", copiedCar.getName());
    }
}
