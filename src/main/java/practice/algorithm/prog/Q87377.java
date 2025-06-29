package practice.algorithm.prog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q87377 {

  public String[] solution(int[][] line) {
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < line.length; i++) {
      for (int j = i + 1; j < line.length; j++) {
        Point intersection = intersection(line[i][0], line[i][1], line[i][2], line[j][0], line[j][1], line[j][2]);
        if (intersection != null) {
          points.add(intersection);
        }
      }
    }

    Point min = min(points);
    Point max = max(points);

    int width = (int) (max.x - min.x + 1);
    int height = (int) (max.y - min.y + 1);

    char[][] arr = new char[height][width];
    for (char[] aChar : arr) {
      Arrays.fill(aChar, '.');
    }

    for (Point point : points) {
      int x = (int) (point.x - min.x);
      int y = (int) (max.y - point.y);
      arr[y][x] = '*';
    }

    String[] result = new String[arr.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = new String(arr[i]);
    }

    return result;
  }

  private Point intersection(long a1,
                             long b1,
                             long c1,
                             long a2,
                             long b2,
                             long c2) {
      double x = (double) (b1 * c2 - b2 * b1) / (a1 * b2 - a2 * b1);
      double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

      if (x % 1 != 0 || y % 1 != 0) {
        return null;
      }

      return new Point((long) x, (long) y);
  }

  private Point min(List<Point> points) {
    long minx = Long.MAX_VALUE;
    long miny = Long.MAX_VALUE;
    for (Point point : points) {
      if (point.x < minx) {
        minx = point.x;
      }

      if (point.y < miny) {
        miny = point.y;
      }
    }

    return new Point(minx, miny);
  }

  private Point max(List<Point> points) {
    long minx = Long.MIN_VALUE;
    long miny = Long.MIN_VALUE;
    for (Point point : points) {
      if (point.x > minx) {
        minx = point.x;
      }

      if (point.y > miny) {
        miny = point.y;
      }
    }

    return new Point(minx, miny);
  }

  static class Point {
    long x;
    long y;

    public Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }
}
