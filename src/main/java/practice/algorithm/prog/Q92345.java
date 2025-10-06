package practice.algorithm.prog;

public class Q92345 {

  private static final int[] dx = {0, 0, -1, 1};
  private static final int[] dy = {-1, 1, 0, 0};

  private static class Coord {

    private final int x;

    private final int y;

    private Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static class Result {

    private final boolean win;

    private final int turns;

    public Result(boolean win, int turns) {
      this.win = win;
      this.turns = turns;
    }
  }

  private Result game(Coord player, Coord opponent, int[][]board) {
    if (board[player.y][player.x] == 0) {
      return new Result(false, 0);
    }

    boolean win = false;
    int winTurns = Integer.MAX_VALUE;
    int loseTurns = Integer.MIN_VALUE;

    board[player.y][player.x] = 0;
    for (int i = 0; i < 4; i++) {
      int nx = player.x + dx[i];
      int ny = player.y + dy[i];
      if (ny < 0 || ny >= board.length ||
          nx < 0 || nx >= board[ny].length) {
          continue;
      }

      if (board[ny][nx] == 0) {
        continue;
      }

      Result res = game(opponent, new Coord(nx, ny), board);
      if (!res.win) {
        win = true;
        winTurns = Math.min(winTurns, res.turns);
      } else if (!win) {
        loseTurns = Math.max(loseTurns, res.turns);
      }
    }

    board[player.y][player.x] = 1;
    if (win) {
    return new Result(true,  winTurns + 1);
    }

    if (loseTurns == Integer.MIN_VALUE) {
      return new Result(false, 0);
    }

    return new Result(false, loseTurns + 1);
}

  public int solution(int[][] board,
                      int[] aloc,
                      int[] bloc) {
    return game(new Coord(aloc[1], aloc[0]),
                new Coord(bloc[1], bloc[0]),
                board).turns;
  }

  static void main() {
    int[] al = {1, 0};
    int[] bl = {1, 2};
    int[][] b = {{1, 1, 1}, {1, 1, 1},{1, 1, 1}};
    IO.println(new Q92345().solution(b, al, bl));
  }
}
