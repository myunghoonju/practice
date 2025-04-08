package practice.algorithm.nossi.ch01.ex;

// https://leetcode.com/problems/word-search/
public class WordSearch {

  static int[] dr = {1, 0, -1, 0};
  static int[] dc = {0, 1, 0, -1};

  static boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    for (int row = 0; row < m; row++) {
      for (int col = 0; col < n; col++) {
        if (board[row][col] != word.charAt(0)) {
          continue;
        }
        char buffer = board[row][col];
        board[row][col] = ' ';
        if (backtrack(board, row, col, word, 0)) {
          return true;
        }

        board[row][col] = buffer;
      }
    }

    return false;
  }

  static boolean backtrack(char[][] board, int row, int col, String word, int index) {
    if (index + 1  == word.length()) {
      return true;
    }

    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < 4; i++) {
      int nr = row + dr[i];
      int nc = col + dc[i];
      if (nr >= 0 &&
          nc >= 0  &&
          nr < m &&
          nc < n) {
        if (board[nr][nc] == word.charAt(index + 1)) {
          char buffer = board[nr][nc];
          board[nr][nc] = ' ';
          if (backtrack(board, nr, nc, word, index + 1)) {
            return true;
          }
          board[nr][nc] = buffer;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    char[][] board =  new char[][] {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
    };

    System.out.println(exist(board , "ADEE"));
  }
}
