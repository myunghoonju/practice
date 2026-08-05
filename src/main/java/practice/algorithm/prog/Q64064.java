package practice.algorithm.prog;

import java.util.*;

public class Q64064 {

  public int solution(String[] user_id, String[] banned_id) {
    Map<Integer, List<String>> ids = new HashMap<>();

    for (int j = 0; j < banned_id.length; j++) {
      for (String user : user_id) {
        if (match(user, banned_id[j])) {
          List<String> list = ids.getOrDefault(j, new ArrayList<>());
          list.add(user);
          ids.put(j, list);
        }
      }
    }

    return permutate(ids, banned_id.length);
  }

  private int permutate(Map<Integer, List<String>> ids, int totalSlots) {
    Set<Set<String>> results = new HashSet<>();
    backtrack(0, totalSlots, ids, new HashSet<>(), new ArrayList<>(), results);
    return results.size();
  }

  private void backtrack(int slot, int totalSlots,
                         Map<Integer, List<String>> ids, Set<String> visited,
                         List<String> current, Set<Set<String>> results) {
    if (slot == totalSlots) {
      results.add(new HashSet<>(current));

      return;
    }

    List<String> candidates = ids.getOrDefault(slot, new ArrayList<>());
    for (String candidate : candidates) {
      if (visited.contains(candidate)) {
        continue;
      }

      visited.add(candidate);
      current.add(candidate);

      backtrack(slot + 1, totalSlots, ids, visited, current,  results);

      visited.remove(candidate);
      current.remove(candidate);
    }
  }

  private boolean match(String user, String banned) {
    if (user.length() != banned.length()) {
      return false;
    }

    for (int i = 0; i < banned.length(); i++) {
      char b = banned.charAt(i);
      char u = user.charAt(i);
      if (b != '*' && Character.toLowerCase(b) != Character.toLowerCase(u)) {
        return false;
      }
    }

    return true;
  }
}
