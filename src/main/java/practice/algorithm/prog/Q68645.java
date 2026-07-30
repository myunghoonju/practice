package practice.algorithm.prog;

public class Q68645 {

  int[] dRow = { 1, 0, -1 };   // 아래, 오른쪽, 대각선(왼쪽 위)
  int[] dCol = { 0, 1, -1 };

  public int[] solution(int n) {
    int total = n * (n + 1) / 2;
    int[][] board = new int[n][n];

    int row = -1;   // 첫 이동(아래, row+1)으로 (0,0)에 도달하도록 한 칸 앞에서 출발
    int col = 0;
    int dir = 0;    // dRow/dCol의 인덱스: 0=아래, 1=오른쪽, 2=대각선
    int steps = n;  // 이번 방향으로 이동할 칸 수
    int num = 1;

    while (num <= total) {
      for (int foot = 0; foot < steps && num <= total; foot++) {
        row += dRow[dir];
        col += dCol[dir];
        board[row][col] = num++;
      }
      dir = (dir + 1) % 3;  // 다음 방향으로 전환
      steps--;              // 다음 방향은 한 칸 짧게
    }

    int[] answer = new int[total];
    int idx = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c <= r; c++) {
        answer[idx++] = board[r][c];
      }
    }
    return answer;
  }
}
