package practice.algorithm.nossi;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// https://81shinez.tistory.com/25
public class KeysAndRooms {

  public static void main(String[] args) {
    canVisitAllRooms(List.of(List.of(1, 3),
                             List.of(3, 0, 1),
                             List.of(2),
                             List.of(0)));
  }

  static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    LinkedList<Integer> openList = new LinkedList<>();
    Set<Object> opened = new HashSet<>();
    opened.add(0);
    openList.add(0);

    while (!openList.isEmpty()) {
      int size = openList.size();
      for (int i = 0; i < size; i++) {
        for (int room : rooms.get(openList.pollFirst())) {
          if (!opened.contains(room)) {
            openList.add(room);
            opened.add(room);
          }
        }
      }
    }
    return opened.size() == rooms.size();
  }
}
