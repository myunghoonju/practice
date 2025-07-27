package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Q72411 {

  private static class Course {

    private String course;

    private int occurrence;

    public Course(String course, int occurrence) {
      this.course = course;
      this.occurrence = occurrence;
    }
  }

  private void courses(char next, Set<String> selected,
                       List<Set<String>> order, Map<Integer, List<Course>> course) {
    int occurrences = (int) order.stream().filter(o -> o.containsAll(selected)).count();
    if (occurrences < 2) {
      return;
    }

    int size = selected.size();
    if (course.containsKey(size)) {
      List<Course> courses = course.get(size);
      Course c = new Course(selected.stream().sorted().collect(Collectors.joining("")), occurrences);
      Course origin = courses.get(0);
      if (origin.occurrence < occurrences) {
        courses.clear();
        courses.add(c);
      } else if (origin.occurrence == occurrences) {
        courses.add(c);
      }
    }

    if (size >= 10) {
      return;
    }

    for (char menuChar = next; menuChar <= 'Z'; menuChar++) {
      String menu = String.valueOf(menuChar);
      selected.add(menu);
      courses((char) (menuChar + 1), selected, order, course);
      selected.remove(menu);
    }
  }

  public String[] solution(String[] orders, int[] course) {
    List<Set<String>> orderList = Arrays.stream(orders)
            .map(String::chars)
            .map(charStream -> charStream.mapToObj(menu -> String.valueOf((char) menu)).collect(Collectors.toSet()))
            .collect(Collectors.toList());

    Map<Integer, List<Course>> courses = new HashMap<>();
    for (int length : course) {
      List<Course> list = new ArrayList<>();
      list.add(new Course("", 0));
      courses.put(length, list);
    }

    courses('A', new HashSet<>(), orderList, courses);

    return courses.values()
            .stream()
            .filter(c -> c.get(0).occurrence > 0)
            .flatMap(List::stream)
            .map(c -> c.course)
            .sorted()
            .toArray(String[]::new);
  }

  public static void main(String[] args) {
    String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
    int[] course = {2,3,4};
    Q72411 q72411 = new Q72411();
    for (String s : q72411.solution(orders, course)) {
      System.err.println(s);
    }
  }
}
