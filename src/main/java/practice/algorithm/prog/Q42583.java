package practice.algorithm.prog;

import java.util.LinkedList;
import java.util.Queue;

public class Q42583 {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int bridgeWeight = 0;
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int truckIndex = 0;

        while (truckIndex < truck_weights.length) {
            bridgeWeight = bridgeWeight - bridge.poll();
            int truckWeight = truck_weights[truckIndex];
            if (bridgeWeight + truckWeight <= weight) {
                bridge.offer(truckWeight);
                bridgeWeight += truckWeight;
                truckIndex++;
            } else {
                bridge.offer(0);
            }

            time++;
        }

        while (bridgeWeight > 0) {
            bridgeWeight = bridgeWeight - bridge.poll();
            time++;
        }

        return time;
    }
}
