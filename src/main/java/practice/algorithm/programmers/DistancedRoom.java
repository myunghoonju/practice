package practice.algorithm.programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/81302
public class DistancedRoom {
  // north, west, east, south
  private static final int[] dx = {0, -1, 1, 0};
  private static final int[] dy = {-1, 0, 0, 1};

  public int[] sol(String[][] places) {
    int[] ans = new int[places.length];
    for (int i = 0; i < ans.length; i++) {
      String[] place = places[i];
      char[][] room = new char[place.length][];
      for (int j = 0; j < room.length; j++) {
        room[j] = place[j].toCharArray();
      }
      if (distanced(room)) {
        ans[i] = 1;
      } else  {
        ans[i] = 0;
      }
    }

    return ans;
  }

  private boolean distanced(char[][] room) {
    for (int i = 0; i < room.length; i++) {
      for (int j = 0; j < room[i].length; j++) {
        if (room[j][i] != 'P') {
          continue;
        }
        if (!distanced(room, i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean distanced(char[][] room,
                            int x,
                            int y) {
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (ny < 0 || ny >= room.length ||
          nx < 0 || nx >= room[ny].length) {
        continue;
      }
      switch (room[ny][nx]) {
        case 'P':
          return  false;
        case 'O':
          if (nextToVolunteer(room, nx, ny, 3 - i)) {
            return false;
          }
          break;
      }
    }

    return true;
  }

  private boolean nextToVolunteer(char[][] room,
                                  int x,
                                  int y,
                                  int except) {
    for (int i = 0; i < 4; i++) {
      if (i == except) {
        continue;
      }
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (ny < 0 || ny >= room.length ||
          nx < 0 || nx >= room[ny].length) {
        continue;
      }
      if (room[ny][nx] == 'P') {
        return true;
      }
    }

    return false;
  }
}
