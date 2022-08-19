package practice.others.java_seventeen;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public class RecordsTest {

    @BeforeEach
    void setUp(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
    }

    record FootBaller(String name, int age, String team) {}

    FootBaller footBaller = new FootBaller("Ronaldo", 38, "Man U");

    @Test
    void testOne() {
        log.info("name {}", footBaller.name);
        log.info("age {}", footBaller.age);
        log.info("team {}", footBaller.team);
        
        record BasketBaller(String name, int age, String team) {}

        boolean sameAthlete = footBaller.equals(new FootBaller("Ozil", 32, "Unkown"));
        boolean sameAthlete2 = footBaller.equals(new BasketBaller("Ronaldo", 38, "Man U"));
        log.info("result {}", sameAthlete);
        log.info("result {}", sameAthlete2);

        int footballerHash = footBaller.hashCode();
        log.info("foodballer's hash {}", footballerHash);

        String footballerToString = footBaller.toString();
        log.info("footballer toString() {}", footballerToString);
    }

    @Test
    void testTwo() {
       record Engineer(String name, int age) {
           Engineer {
               if (age < 1) {
                   throw new IllegalArgumentException("less than 1 not allowed");
               }
               name = name.toUpperCase();
           }

           public int agePlusOne() {
               return age + 1;
           }
       }

        Engineer engineer = new Engineer("Onur", 39);
        log.info("engineer {}", engineer);
        log.info("engineer age {}", engineer.agePlusOne());
    }
}
