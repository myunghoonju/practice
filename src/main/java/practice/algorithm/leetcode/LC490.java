package practice.algorithm.leetcode;

public class LC490 {

  public boolean hasPath(int[][] maze,
                         int[] start,
                         int[] destination) {
    int row = maze.length;
    int col = maze[0].length;
    boolean[][] visited = new boolean[row][col];

    return dfs(maze, start[0], start[1], destination, visited);
  }

  private boolean dfs(int[][] maze,
                      int row,
                      int col,
                      int[] destination,
                      boolean[][] visited) {
    if (row == destination[0] &&
        col == destination[1]) {
      return true;
    }

    if (visited[row][col]) {
      return false;
    }

    visited[row][col] = true;

    return move(maze, row, col, destination, 1, 0, visited)||
           move(maze, row, col, destination, -1, 0, visited)||
           move(maze, row, col, destination, 0, 1, visited)||
           move(maze, row, col, destination, 0, -1, visited);
  }

  private boolean move(int[][] maze,
                       int row,
                       int col,
                       int[] dest,
                       int drow,
                       int dcol,
                       boolean[][] visited) {
    int rows = maze.length;
    int cols = maze[0].length;

    int r = row;
    int c = col;
    while (r + drow >= 0 &&
           r + drow <= rows - 1 &&
           c + dcol >= 0 &&
           c + dcol <= cols - 1 &&
           maze[r + drow][c + dcol] == 0) {
      r = r + drow;
      c = c + dcol;
    }

    return dfs(maze, r, c, dest, visited);
  }

  static void main() {

  }
}
