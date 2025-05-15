package practice.algorithm.nossi.ch01;

public class SudoKu {

  public static void main(String[] args) {
    char[][] sample = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    SudoKu sudoKu = new SudoKu();
    sudoKu.solveSudoku(sample);

    for (char[] chars : sample) {
      for (char aChar : chars) {
        System.out.print(aChar + ", ");
      }
    }
  }

  // 1~9까지 숫자
  // 가로, 세로 줄에 동일 숫자 한번만
  // 3x3영역에 1-9 다 나와야 함
  // 9x9가 전체영역
  public void solveSudoku(char[][] board) {
    char[][] cp = new char[board.length][];
    for (int i = 0; i < board.length; i++) {
      cp[i] = board[i].clone();
    }

    backTrack(cp, 0);

    for (int i = 0; i < board.length; i++) {
      board[i] = cp[i].clone();
    }
  }

  boolean backTrack(char[][] board, int n) {
    if (n == 81) {
      return true;
    }

    int r = n / 9;
    int c = n % 9;
    if (board[r][c] != '.') {
      return backTrack(board, n + 1);
    } else {
      for (int i = 0; i < 9; i++) {
        char next = (char) (49 + i);
        if (!valid(board, r, c, next)) {
          continue;
        }
        board[r][c] = next;
        if (backTrack(board, n + 1)) {
          return true;
        }

        board[r][c] = '.';
      }
    }

    return false;
  }

  boolean valid(char[][] board,
                int r,
                int c,
                char next) {
    for (int i = 0; i < 9; i++) {
      if (board[i][c] == next) {
        return false;
      }
    }

    for (int i = 0; i < 9; i++) {
      if (board[r][i] == next) {
        return false;
      }
    }

    int subr = (r / 3) * 3;
    int subc = (c / 3) * 3;
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        if (board[subr + k][subc + l] == next) {
          return false;
        }
      }

    }
    return true;
  }

  boolean sol2(char[][] board, int r, int c) {
    if (r == board.length) {
      return true;
    }

    if (c == board[0].length) {
      return sol2(board, r + 1, 0);
    }

    if (board[r][c] != '.') {
      return sol2(board, r, c + 1);
    }

    for (char num = '1'; num <= '9'; num++) {
      if (valid2(board, r, c, num)) {
        board[r][c] = num;
        if (sol2(board, r, c + 1)) {
          return true;
        }
        board[r][c] = '.';
      }
    }
    return false;
  }

  boolean valid2(char[][] board, int r, int c, char next) {
    for (int i = 0; i < board.length; i++) {
      if (board[i][c] == next) {
        return false;
      }

      if (board[r][i] == next) {
        return false;
      }

      int subr = 3 * (r / 3) + i / 3;
      int subc = 3 * (c / 3) + i % 3;
      if (board[subr][subc] == next) {
        return false;
      }
    }
    return true;
  }
}
