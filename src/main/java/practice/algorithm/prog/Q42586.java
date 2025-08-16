package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q42586 {

    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            q.offer(progresses[i]);
        }

        int days = 0;
        int count = 0;
        List<Integer> list = new LinkedList<>();
        while (!q.isEmpty()) {
            int idx = q.poll();
            int expire = (int) Math.ceil((double) (100 - progresses[idx]) / speeds[idx]);

            if (expire > days) {
                if (days != 0) {
                    list.add(count);
                    count = 0;
                }

                days = expire;
            }

            count++;
        }

        list.add(count);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}