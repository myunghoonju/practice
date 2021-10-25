package practice.algorithm.ch02;

/*

첫 줄에는 하늘에 떠 있는 천체의 수 N이 주어진다. N은 2이상 1,000이하의 자연수이다.
그 후 총 N줄에 걸쳐서 각 줄에는 한 천체의 위치를 좌표로 나타내는 두 정수가 주어진다.
천체의 정보를 포함하는 N개의 각 줄에는 천체의 x좌표와 y좌표를 나타내는 두 정수가 공백으로 구분되어 주어진다.
모든 좌표는 절대값이 1만 이하인 정수이다. 모든 천체의 좌표는 서로 다르다.


INPUT
5
10000 9999
-10000 -9999
10000 -9999
-10000 9999
0 0

OUTPUT
14141.4
4

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class PointTwoDimension {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(br.readLine());
        BigDecimal minSqd = new BigDecimal(String.valueOf(Double.MAX_VALUE)).setScale(1, RoundingMode.HALF_UP);
        int minCnt = 0;
        TwoDimension[] twoDimensions = new TwoDimension[range];

        for (int i = 0; i < range; i++) {
            int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            twoDimensions[i] = new TwoDimension(data[0], data[1]);
        }

        for (int i = 0; i < range; i++) {
            for (int j = 0; j < i; j++) {
                double sqd = twoDimensions[i].getSqrtDist(twoDimensions[j]);
                BigDecimal sqdB = new BigDecimal(String.valueOf(sqd)).setScale(1, RoundingMode.HALF_UP);
                int result = minSqd.compareTo(sqdB);
                if (result > 0) {
                    minCnt = 1;
                    minSqd = sqdB;
                }
                if (result == 0) {
                    minCnt++;
                }
            }
        }
        System.out.println(minSqd);
        System.out.print(minCnt);
    }

    static class TwoDimension {
        int x;
        int y;

        public TwoDimension(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Double getSqrtDist(TwoDimension twoDimension) {
            int dx = Math.abs(twoDimension.x - this.x);
            int dy = Math.abs(twoDimension.y - this.y);

            BigDecimal bigDecimal = new BigDecimal((dx * dx) + (dy * dy));
            return Math.sqrt(bigDecimal.doubleValue());
        }
    }
}