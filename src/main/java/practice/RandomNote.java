package practice;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RandomNote {

  public static void main(String[] args) {
    System.out.println("Random note");
  }

  // east, south, west, north
  public static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  int bfs(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    if (grid[0][0] == 1 ||grid[row - 1][col -1] == 1) { // meet the end
      return -1;
    }

    boolean[][] visit = new boolean[row][col];
    visit[0][0] = true;
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, 0});
    int length = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] polled = queue.poll();
        if (polled[0] == row -1 && polled[1] == col -1) {
          return length;
        }
        for (int j = 0; j < DIRECTIONS.length; j++) {  // east, south, west, north
          int nextRow = polled[0] + DIRECTIONS[j][0];
          int nextCol = polled[1] + DIRECTIONS[j][1];
          if (nextRow >= 0 && // move!
              nextCol >= 0 &&  // move!
              nextRow < row && // not end
              nextCol < col && // not end
              !visit[nextRow][nextCol] && // never visited
              grid[nextRow][nextCol] == 1) { // it means available path
            visit[nextRow][nextCol] = true;
          }
        }
      }
      length++;
    }

    return -1;
  }

  class Pair {
    private int key;
    private int value;

    public void setKey(int key) {
      this.key = key;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getKey() {
      return key;
    }

    public int getValue() {
      return value;
    }

    public Pair(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}
