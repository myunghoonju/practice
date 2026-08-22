package practice.algorithm.prog;

public class Q121687 {

  enum Direction {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0)
    ;

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
      this.dx = dx;
      this.dy = dy;
    }
  }

  record State(int x, int y, Direction direction) {}

  public State move(State state, String command) {
    int x = state.x();
    int y = state.y();
    Direction direction = state.direction();

    if (command.equals("R")) {
      // TODO: direction을 오른쪽으로 회전시키기
      direction = Direction.values()[(direction.ordinal() + 1) % 4];
    }

    if (command.equals("L")) {
      // TODO: direction을 왼쪽으로 회전시키기
      direction = Direction.values()[(direction.ordinal() - 1 + 4) % 4];

    }

    if (command.equals("G")) {
      x = x + direction.dx;
      y = y + direction.dy;
    }

    if (command.equals("B")) {
      x = x - direction.dx;
      y = y - direction.dy;
    }

    return new State(x, y, direction);
  }

  public int[] solution(String command) {
    State state = new State(0, 0, Direction.UP);
    for (String cmd : command.split("")) {
      state = move(state, cmd);
    }
    return new int[] {state.x(), state.y()};
  }
}
