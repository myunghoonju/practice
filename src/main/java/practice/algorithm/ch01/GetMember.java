package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

사막 여우의 탈을 쓴 지수는 놀이공원에서 아르바이트를 하고 있습니다.
지수가 담당하고 있는 놀이기구는 무게에 민감하기 때문에 몇 가지 제한을 두고 있습니다.
이 놀기기구는 몸무게가 P kg이 넘는 사람은 탑승 할 수 없습니다.
그리고 탑승한 승객의 몸무게의 총 합은 Q kg을 넘을 수 없습니다.
다만 탑승하는 인원의 제한은 두고 있지 않습니다.
보통은 놀이기구의 최대 탑승 중량을 넘기 전에 탑승 인원을 나누기 때문에 크게 문제가 발생하지 않습니다.
하지만 오늘은 조금 문제가 있습니다.
아주대학교 알고리즘 동아리라고 자신들을 소개한 단체손님 때문입니다.
그들은 애초에 몸무게가 P kg를 넘어가는 회원은 제외하더라도 나머지 인원들은 꼭 함께 놀이기구를 탑승해야한다고 우기고 있습니다.
이 동아리의 회원들 중 놀이기구를 탈 수 있는 사람은 몇 명인지, 그리고 체중 제한을 통과한 사람들은 모두 함께 놀이기구를 탈 수 있는지 계산할 수 있는 프로그램을 작성해주세요.

*/
public class GetMember {
    // outliers
    // too heavy p >
    // too many q >
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        String[] data = br.readLine().split(" ");
        getMember(params, data);
    }

    public static void getMember(String[] params, String[] data) {
        int maxWeight = Integer.parseInt(params[1]);
        int maxSum = Integer.parseInt(params[2]);
        int peopleSum = 0;
        int weightSum = 0;

        for (String weightStr : data) {
           int weightNum = Integer.parseInt(weightStr);
           if (weightNum <= maxWeight){
               peopleSum++;
               weightSum+=weightNum;
           }
        }
        if (weightSum <= maxSum) {
            System.out.println(peopleSum+" "+weightSum);
            System.out.println("YES");
        } else {
            System.out.println(peopleSum+" "+weightSum);
            System.out.println("NO");
        }
    }
}
