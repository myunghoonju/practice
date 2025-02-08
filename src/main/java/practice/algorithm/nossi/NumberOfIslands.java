package practice.algorithm.nossi;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@Slf4j
public class NumberOfIslands {

  private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public int numIslands(char[][] grid) {
    return sol(grid);
  }

  private int sol(char[][] grid) {
    int answer = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          //breadthFirstSearch(grid, i, j);
          depthFirstSearch(grid, i, j);
          ++answer;
        }
      }
    }

    return answer;
  }

  // mark 9 as visited
  private void breadthFirstSearch(char[][] grid, int i, int j) {
    Queue<Pair> queue = new ArrayDeque<>(List.of(new Pair(i, j))); // use queue in bfs algorithm
    grid[i][j] = '9';
    while (!queue.isEmpty()) {
      int r = queue.poll().getKey();
      int c = queue.poll().getValue();
      for (int[] dir : DIRECTIONS) {
        int newR = r + dir[0];
        int newC = c + dir[1];
        if (newR < 0 || newR == grid.length ||
            newC < 0 || newC == grid[0].length) {
          continue;
        }
        if (grid[newR][newC] != '1') {
          continue;
        }

        queue.offer(new Pair(newR, newC));
        grid[newR][newC] = '9';
      }
    }

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

  // mark 9 as visited
  private void depthFirstSearch(char[][] grid, int i, int j) {
    if (i < 0 || i == grid.length ||
        j < 0 || j == grid[0].length) {
      return;
    }

    if (grid[i][j] != '1') {
      return;
    }

    grid[i][j] = '9';
    depthFirstSearch(grid, i + 1, j);
    depthFirstSearch(grid, i - 1, j);
    depthFirstSearch(grid, i, j + 1);
    depthFirstSearch(grid, i, j - 1);
  }


  public static void main(String[] args) {
    char[][] grid = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}};

    System.out.println("-> "+ new NumberOfIslands().numIslands(grid));
  }
}
