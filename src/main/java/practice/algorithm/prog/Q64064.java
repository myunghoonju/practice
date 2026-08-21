package practice.algorithm.prog;

import java.util.HashSet;
import java.util.Set;

public class Q64064 {

  private final Set<Set<String>> result = new HashSet<>();

  public int solution(String[] user_id, String[] banned_id) {
    match(0, user_id, banned_id, new HashSet<>());
    return result.size();
  }

  private Set<String> match(int banIdx, String[] user_id, String[] banned_id, Set<String> uniqueList) {
    if (banIdx == banned_id.length) {
      result.add(new HashSet<>(uniqueList));
      return  uniqueList;
    }


    String target = banned_id[banIdx];
    for (String user : user_id) {
      boolean related = true;
      String[] splitUser = user.split("");
      String[] splitTarget = target.split("");
      if (user.length() != target.length()) {
        continue;
      }

      for (int tIdx = 0; tIdx < splitTarget.length; tIdx++) {
        if (splitTarget[tIdx].equals("*")) {
          continue;
        }

        if (!splitTarget[tIdx].equals(splitUser[tIdx])) {
          related = false;
        }
      }


      boolean preExist = uniqueList.stream().anyMatch(m -> m.equals(user));
      if (related && !preExist) {
        uniqueList.add(user);
        match(banIdx + 1, user_id, banned_id, uniqueList);
        uniqueList.remove(user);
      }
    }

    return uniqueList;
  }
}
