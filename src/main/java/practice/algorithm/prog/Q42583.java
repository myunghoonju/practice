package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q42583 {

    public int solution(int bridge_length,
                        int weight,
                        int[] truck_weights) {
        int time = 0;
        int currentWeight = 0;
        int truckIdx = 0;
        Queue<Integer> bridge = new LinkedList<>();

        // 1) 다리를 0으로 초기화 (길이만큼)
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        while (truckIdx < truck_weights.length) {
            time++;
            currentWeight -= bridge.poll(); // 다리 앞에서 빠져나간 트럭 무게 제거

            if (currentWeight + truck_weights[truckIdx] <= weight) {
                // 2) 트럭 올라갈 수 있을 때: ___
                currentWeight += truck_weights[truckIdx];
                bridge.offer(truck_weights[truckIdx]);
                truckIdx++;
            } else {
                // 3) 못 올라갈 때: ___
                bridge.offer(0);
            }
        }

        // 4) 마지막 트럭까지 다리를 건너는 시간: ___
        time += bridge_length;

        return time;
    }
}
