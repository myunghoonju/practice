package practice.algorithm.nossi.ch02;

// https://eunplay.tistory.com/118
// https://school.programmers.co.kr/learn/courses/30/lessons/118667
public class EvenQueue {

  public int sol2(int[] queue1, int[] queue2) {
    int[] queue = new int[queue1.length + queue2.length];
    System.arraycopy(queue1, 0, queue, 0, queue1.length);
    System.arraycopy(queue2, 0, queue, queue1.length, queue1.length);

    long q1s = sum(queue1);
    double target = (sum(queue1) + sum(queue2)) / 2.0;

    int p1 = 0;
    int p2 = queue1.length;
    for (int answer = 0; answer < 3 * queue1.length; answer++) {
      if (q1s > target) {
        q1s -= queue[p1];
        p1 = (p1 + 1) % queue.length;
      } else if (target > q1s){
        q1s += queue[p2];
        p2 = (p2 + 1) % queue.length;
      } else {
        return answer;
      }
    }

    return -1;
  }

  private long sum(int[] arr) {
    long sum = 0;
    for(long num : arr) {
      sum += num;
    }

    return sum;
  }
}
