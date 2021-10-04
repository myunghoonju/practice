package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*

데이터 마이닝 과목을 수강하게 된 지애는 평소 수학과 컴퓨터와는 거리가 먼 삶을 살아왔기 때문에 과제에 애를 먹고있다.
평소 지애 괴롭히기를 좋아하는 미주는 진지하게 휴학을 고민하는 지애가 학교를 떠나는 것을 원하지 않기에 과제를 대신 해주기로 했다.
하지만 미주도 프로그래밍을 전혀 할 줄 모르기 때문에 평소 컴퓨터를 잘 다루기로 소문난 선배인 당신에게 과제를 부탁했다. 당신은 미주를 위하여 프로그램을 작성해야 한다.
N개의 수치 데이터가 정수로 주어진다. 당신은 이 개별 데이터들 중 전체 데이터의 평균과 가장 가까운 데이터와 그 번호를 출력하고자 한다.
가깝다라는 의미는 평균에서 그 숫자를 뺀 절댓값이 작다는 의미이다. 평균과의 거리가 같은 숫자가 두 개 이상일 경우, 가장 먼저 입력된 데이터를 우선으로 한다.
데이터의 번호는 입력받은 순서대로 1부터 N으로 부여된다.

*/
public class SearchIndexThree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int denominator = Integer.parseInt(br.readLine());
        String[] range = br.readLine().split(" ");
        int sum = 0;
        int avg = 0;
        int index = 0;
        Integer[] rangeInt = new Integer[denominator];

        for (String data: range) {
            rangeInt[index++] = Integer.parseInt(data);
        }
        int m = 0;
        sum = Arrays.stream(rangeInt).mapToInt(Integer::intValue).sum();
        for (int i = 0; i < denominator; i++) {
            int a = Math.abs(rangeInt[m] * denominator - sum);
            int b = Math.abs(rangeInt[i] * denominator - sum);
            if (a > b) {
                m = i;
            }
        }
        int indexNo = m + 1;
        System.out.println(indexNo  + " " + rangeInt[m]);
    }
}
