package practice.others.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetsTest {

    List<Target> list = new ArrayList<>();

    @BeforeEach
    void init() {
        Target one = new Target("A", 20L);
        Target two = new Target("B", 10L);
        Target three = new Target("C", 15L);
        list.add(one);
        list.add(two);
        list.add(three);
    }

    @Test
    void alphabetical_order_test() {
        List<Target> targets = Targets.sortByName(list);
        assertThat(targets.get(0).getName()).isEqualTo("A");
    }
    @Test
    void numerical_order_test() {
        List<Target> targets = Targets.sortBySal(list);
        assertThat(targets.get(1).getName()).isEqualTo("C");
    }

    @Test
    void sort_by_opt() {
        List<Target> targets = Targets.sort(Operate.NAME.name(), list);
        assertThat(targets.get(1).getName()).isEqualTo("B");
    }

    @Test
    void sort_by_opt2() {
        List<Target> targets = Targets.sort(Operate.SALARY.name(), list);
        assertThat(targets.get(1).getName()).isEqualTo("C");
    }
}
