package practice.algorithm.prog;

public class Q121687 {

  // 0 = north 1 = east 2 = south 3 = west
  public int[] solution(String command) {
    int point = 0;
    int[] answer = new int[] {0, 0};
    String[] orders = command.split("");
    for (String order : orders) {
      point = direct(point, order);
      move(answer, point, order);
    }

    return answer;
  }

  private int direct(int point, String order) {
    if ("R".equals(order)) {
      if (point == 3) {
        return 0;
      }

      point += 1;
    }

    if ("L".equals(order)) {
      if (point == 0) {
        return 3;
      }

      point -= 1;
    }

    return point;
  }

  private void move(int[] answer, int point, String order) {
    int x = answer[0];
    int y = answer[1];
    if (point == 0) {
      if ("G".equals(order)) {
        y += 1;
      }

      if ("B".equals(order)) {
        y -= 1;
      }
    }

    if (point == 1) {
      if ("G".equals(order)) {
        x += 1;
      }

      if ("B".equals(order)) {
        x -= 1;
      }
    }

    if (point == 2) {
      if ("G".equals(order)) {
        y -= 1;
      }

      if ("B".equals(order)) {
        y += 1;
      }
    }

    if (point == 3) {
      if ("G".equals(order)) {
        x -= 1;
      }

      if ("B".equals(order)) {
        x += 1;
      }
    }

    answer[0] = x;
    answer[1] = y;
  }
}
