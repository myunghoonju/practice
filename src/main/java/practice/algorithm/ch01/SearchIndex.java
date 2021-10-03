package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

N개의 정수로 이루어진 배열과 찾고자 하는 값 M이 주어진다.
이 배열에서 M이 존재하는 인덱스를 출력하는 프로그램을 작성하시오.

*/
public class SearchIndex {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        String[] data = br.readLine().split(" ");
        System.out.println(getIndex(params, data));
    }

    public static int getIndex(String[] params, String[] data) {
        int index = -1;
        int target = Integer.parseInt(params[1]);
        for (int i = 0; i < data.length; i++) {
            int number = Integer.parseInt(data[i]);
            if (target == number) {
                index = i;
            }
        }
        return index;
    }
}
