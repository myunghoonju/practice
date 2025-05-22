package practice.algorithm;

public class FloodFill {

  public int[][] floodFill(int[][] image,
                           int sr,
                           int sc,
                           int color) {
    int initColor = image[sr][sc];
    if (initColor == color) {
      return image;
    }

    int[][] result = new int[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        result[i][j] = image[i][j];
      }
    }

    int[] dirRow = {-1, 0, 1, 0};
    int[] dirCol = {0, 1, 0, -1};

    dfs(sr, sc, image, result, color, initColor, dirRow, dirCol);

    return result;
  }

  private void dfs(int row,
                   int col,
                   int[][] image,
                   int[][] result,
                   int newColor,
                   int initColor,
                   int[] dirRow,
                   int[] dirCol) {
    result[row][col] = newColor;
    int n = image.length;
    int m = image[0].length;

    for (int i = 0; i < 4; i++) {
      int nrow = row + dirRow[i];
      int ncol = col + dirCol[i];
      if (nrow >= 0 &&
          ncol >= 0 &&
          nrow < n &&
          ncol < m &&
          image[nrow][ncol] == initColor &&
          result[nrow][ncol] != newColor) {
        dfs(nrow, ncol, image, result, newColor, initColor, dirRow, dirCol);
      }
    }
  }
}
