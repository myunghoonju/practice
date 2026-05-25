package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.List;

public class Q42586 {

    public int[] solution(int[] progresses, int[] speeds) {
      int[] days = new int[progresses.length];
      for (int i = 0; i < progresses.length; i++) {
        days[i] = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
      }

      // 2) 배포 그루핑
      List<Integer> result = new ArrayList<>();
      int i = 0;
      while (i < days.length) {
        int maxDay = days[i]; // 현재 배포 기준일
        int count = 0;
        while (i < days.length && days[i] <= maxDay) { // 기준일 이하면 같은 배포
          count++;
          i++;
        }

        result.add(count);
      }

      return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
