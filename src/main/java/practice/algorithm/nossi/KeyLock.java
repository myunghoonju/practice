package practice.algorithm.nossi;

public class KeyLock {

  public static void main(String[] args) {

  }

  static boolean solution(int[][] key, int[][] lock) {
    int n = lock.length;
    int m = key.length;
    for (int offsetRow = m; offsetRow > -n; offsetRow--) {
      for (int offsetCol = m; offsetCol > -n; offsetCol--) {
        if (open(offsetCol, offsetRow, key, lock)) {
          return true;
        }
      }
    }

    return false;
  }

  static boolean open(int offsetCol, int offsetRow,
                      int[][] key, int[][] lock) {
    int n = lock.length;
    int m = key.length;
    for (int rot = 0; rot < 4; rot++) {
      boolean match = true;

      // loop labeling
      outer:
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n; c++) {
          int keyPoint = 0;
          int keyCol = c + offsetCol;
          int keyRow = r + offsetRow;
          if (keyCol >= 0 && keyCol < m &&
              keyRow >= 0 && keyRow < m) {
            keyPoint = keyPoint(key, keyCol, keyRow, rot);
          }
          if ((lock[r][c] == 1 && keyPoint == 1) ||
              (lock[r][c] == 0 && keyPoint == 0)) {
            match = false;
            break outer;
          }
        }
      }

      if (match) {
        return true;
      }
    }

    return false;
  }

  static int keyPoint(int[][] key, int keyCol, int keyRow, int rot) {
    int m = key.length;
    switch (rot) {
      case 0: return key[keyRow][keyCol];
      case 1: return key[keyCol][m-1-keyRow];
      case 2: return key[m-1-keyRow][m-1-keyCol];
      case 3: return key[m-1-keyCol][keyRow];
    }

    return -1;
  }
}
