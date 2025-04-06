package practice.algorithm.nossi.ch01;

public class KeyLock {

  public static void main(String[] args) {
    int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
    int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

    System.out.println(solution(key, lock));
    System.out.println(sol2(key, lock));
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
      boolean fin = false;
      boolean match = true;
      for (int r = 0; r < n; r++) {
        if (fin) {
          break;
        }

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
            fin = true;
            break;
          }
        }
      }

      if (match) {
        return true;
      }
    }

    return false;
  }

  private static int keyPoint(int[][] key, int keyCol, int keyRow, int rot) {
    int m = key.length;
    return switch (rot) {
      case 0 -> key[keyRow][keyCol];
      case 1 -> key[keyCol][m - 1 - keyRow];
      case 2 -> key[m - 1 - keyRow][m - 1 - keyCol];
      case 3 -> key[m - 1 - keyCol][keyRow];
      default -> -1;
    };
  }

  static boolean sol2(int[][] key, int [][] lock) {
    for (int i = 0; i < 4; i++) {
      if (i > 0) {
        key = rotate(key);
      }

      for (int x = -(lock.length - 1); x < (lock.length - 1); x++) {
        for (int y = -(lock.length - 1); y < (lock.length - 1); y++) {
          if (match(key, lock, x, y)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  static int[][] rotate(int[][] key) {
    int[][] res = new int[key.length][key.length];
    for (int i = 0; i < key.length; i++) {
      for (int j = 0; j < key.length; j++) {
        res[i][j] = key[key.length - 1 - j][i];
      }
    }

    return res;
  }

  static boolean match(int [][] key,
                int [][] lock,
                int x,
                int y) {
    for (int i = 0; i < lock.length; i++) {
      for (int j = 0; j < lock.length; j++) {
        if (i + x < 0 ||
            j + y < 0 ||
            i + x >= key.length ||
            j + y >= key.length) {
          if (lock[i][j] == 0) {
            return false;
          }
        } else {
          if (lock[i][j] == key[x + i][y + j]) {
            return false;
          }
        }
      }
    }

    return true;
  }
}
