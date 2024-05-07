package practice.others.thread;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ParallelTask {

    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        A a = new A("1");
        A b = new A("2");
        A c = new A("3");
        A d = new A("-");
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        list.parallelStream().forEach(aa -> {
                                              // 1, heap saved obj
                                              // list2.add(aa.data);
                                               print(list2, aa);});
    }

    static void print(List<String> list, A a) {
        // 2. can occur ConcurrentModificationException
        // list.add(a.data);
        list.forEach(System.err::println);

    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class A {
        String data;
    }
}
