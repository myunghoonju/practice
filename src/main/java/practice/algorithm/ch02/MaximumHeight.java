package practice.algorithm.ch02;

/*

도토리 나라의 도토리들이 다니는 도토리 고등학교에서는 매 달 재미있는 이벤트를 한다.
도토리 나라에서는 다른 도토리들 보다 큰 키를 가지는 것이 큰 경쟁력 중 하나였기에,
도토리 고등학교에서는 매 달마다 그 달에 생일이 있는 도토리들 중 가장 큰 키를 가진 도토리에게 상장을 수여한다.
N개의 도토리에 대한 키와 생일 정보가 주어진다.
이 때 이번 달에 생일이 있는 도토리들 중 가장 키가 큰 도토리의 키를 출력하는 프로그램을 작성하자.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximumHeight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(br.readLine());
        String[] heightStr = br.readLine().split(" ");
        String[] birthStr = br.readLine().split(" ");
        int targetBirth = Integer.parseInt(br.readLine());

        // find birth index
        int index = 0;
        for (int i = 0; i < range; i++) {
            int birth = Integer.parseInt(birthStr[i]);
            if (birth == targetBirth) {
                index = i;
            }
        }
        if (index == 0) {
            System.out.println(-1);
        } else {
            System.out.println(heightStr[index]);
        }
    }
}
