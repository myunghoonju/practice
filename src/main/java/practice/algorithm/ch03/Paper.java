package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Paper {

    private static final int MAX_PAPER = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNo = Integer.parseInt(br.readLine()); // 2

        for (int i = 0; i < caseNo; i++) {
            int paperNo = Integer.parseInt(br.readLine()); // 3
            Papers[] papers = new Papers[paperNo];
            for (int index = 0; index < paperNo; index++) {
                int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int x = xy[0];
                int y = xy[1];
                papers[index] = new Papers(x, y);
            }

            System.out.println(getArea(papers, paperNo));
        }
    }

    static int getArea(Papers[] papers, int n) {
        int answer = 0;
        int[][] board = new int[MAX_PAPER][MAX_PAPER];

        for (Papers p : papers) {
            for (int row = p.bottomRow; row <= p.topRow; row++) {
                for (int col = p.leftColumn; col <= p.rightColumn; col++) {
                    board[row][col] += 1;
                }
            }
        }

        for (int row = 0; row < MAX_PAPER; row++) {
            for (int col = 0; col < MAX_PAPER; col++) {
                answer += 1;
            }
        }

        return answer;
    }

    static class Papers {
        int leftColumn;   //가장 왼쪽 격자의 열 번호
        int rightColumn;  //가장 오른쪽 격자의 열 번호
        int topRow;       //가장 위쪽 격자의 행 번호
        int bottomRow;

        Papers(int xPos, int yPos) {
            this.leftColumn = xPos;
            this.bottomRow = yPos;
            // 가로세로 길이가 각각 10
            this.rightColumn = this.leftColumn + 9;
            this.topRow = this.bottomRow + 9;
        }
    }
}
