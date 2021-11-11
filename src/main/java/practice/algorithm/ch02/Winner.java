package practice.algorithm.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Winner {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] summaryData = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        isWinnable(data, summaryData[0], summaryData[1]);
    }

    /*
     * 오른쪽방향으로 인덱스를 옮겨가며 합이 짝수인 경우를 찾는 함수
     * @param data 자료
     * @param n 자료의 수
     * @param k 선택가능 개수
     * @return true 현무가 승리할 수 있는 경우의 수가 하나 이상 존재
     */
    public static void isWinnable(int[] data, int n, int k) {
        int winCount = 0;
        long sum = 0;

        // 첫 (k-1)개의 원소에 대한 합을 계산한다.
        for(int i = 0 ; i < k - 1 ; i++) {
            sum += data[i];
        }

        for(int i = 0; i + k - 1 < n ; i++) {   //영역의 왼쪽 끝 인덱스 i에 대해
            //영역을 벗어나게 되는 원소 제외
            if (i > 0) {
                sum -= data[i-1];
            }

            //새로 영역에 들어온 원소 추가
            sum = sum + data[i + k-1];

            if(sum % 2 == 0) {
                winCount += 1;
                break;
            }
        }

        if (winCount > 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
