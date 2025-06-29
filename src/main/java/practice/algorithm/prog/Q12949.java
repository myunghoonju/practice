package practice.algorithm.prog;

import java.util.Arrays;
import java.util.List;

public class Q12949 {

  public int[][] calc(int[][] arr1, int[][] arr2) {
    int[][] arr = new int[arr1.length][arr2[0].length];
    for (int i = 0; i < arr.length; i++) { // row
      for (int j = 0; j < arr[i].length; j++) { // col
        arr[i][j] = 0;
        for (int k = 0; k < arr1[i].length; k++) {
          arr[i][j] += arr1[i][k] * arr2[k][j];
        }
      }
    }

    return arr;
  }

  public static void main(String[] args) {
    Q12949 m = new Q12949();
    int[][] arr1 = {{1,4}, {3,2}, {4,1}};
    int[][] arr2 = {{3,3}, {3,3}};
    List.of(m.calc(arr1, arr2)).forEach(it -> Arrays.stream(it).forEach(System.out::println));
  }
}
