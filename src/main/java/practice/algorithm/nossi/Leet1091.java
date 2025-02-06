package practice.algorithm.nossi;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-binary-matrix/description
public class Leet1091 {

  private static final int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, -1 }, { -1, 1 }, { -1, -1 }, { 1, 1 } };


  private static int solWithBfs(int [][] grid) {
    int x = grid.length;
    int y = grid[0].length;

    if (grid[0][0] == 1 || grid[x-1][y-1] == 1) {
      return -1;
    }

    boolean[][] visited = new boolean[x][y];
    visited[0][0] = true;
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, 0});
    int length = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] polled = queue.poll();
        if (polled[0] == x - 1 && polled[1] == y - 1) {
          return length;
        }
        for (int j = 0; j < dir.length; j++) {
          int newX = dir[j][0] + polled[0];
          int newY = dir[j][1] + polled[1];
          if (newX >= 0 &&
              newY >= 0 &&
              newX < x &&
              newY < y &&
              !visited[newX][newY] &&
              grid[newX][newY] == 0) {
            queue.add(new int[]{newX, newY});
            visited[newX][newY] = true;
          }
        }
      }
      length++;
    }

    return -1;
  }

  public static void main(String[] args) {
    int[][] g = {{ 0,0,0}, { 1,1,0}, { 1,1,0}};
    System.out.println(solWithBfs(g));
  }
}
