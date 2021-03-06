package practice.algorithm.ch02;

/*

가수의 앨범은 팬들에게는 중요한 존재이다.
긴 기다림의 끝에 마주친 오아시스 같은 존재이기 때문이다.
그렇기에 최근의 가수들은 자신의 앨범에 기념품이나 손편지 등을 추가하여 팬들에게 고마운 마음을 전하고있다.
인기 많은 아이돌 트와이스의 열렬한 팬인 민규는 이번에도 앨범을 사기 위하여 신촌의 한 백화점에 줄을 서서 대기하고있다.
이 백화점에서 판매하는 앨범은 한정판으로서, 각 앨범에는 임의의 화보가 한 장 들어있다.
이미 입대가 얼마남지 않은 민규는 자신의 모든 재산을 털어 트와이스 앨범을 구매하여 모든 종류의 화보를 수집하고자 한다.
각 화보들은 종류별로 고유한 번호를 가지고있으며 이 번호를 통해 같은 화보인지 아닌지 구분할 수 있다.
물론 여러 앨범들에는 같은 화보가 들어있을 수 있다. 민규는 총 N개의 앨범을 구매하여 화보들을 번호 순서대로 정렬하여 보관하려고 한다.
이 때 민규는 자신이 총 몇 종류의 화보를 획득하였는지 궁금해졌고, 코딩을 잘하는 당신에게 이를 계산하는 프로그램을 작성해주기를 부탁하였다.
민규의 마지막 소원을 들어주기 위하여 프로그램을 작성하시오.

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class ElementTypeCount {

    public static void main(String[] args) throws IOException {
        // 중복없는 자료 개수 반환(모두 -1인경우 1 반환)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(br.readLine());
        String[] data = br.readLine().split(" ");

        System.out.println(getElementTypeCount(data, range));
    }

    public static int getElementTypeCount(String[] data, int range) {
        int minusCnt = 0;
        LinkedHashSet<Integer> noDupData = new LinkedHashSet<>();
        for (String numStr : data) {
            int num = Integer.parseInt(numStr);
            if (num < 0) {
                minusCnt++;
            }
            noDupData.add(num);
        }

        if (minusCnt == range) {
            return 1;
        }
        return noDupData.size();
    }
}
