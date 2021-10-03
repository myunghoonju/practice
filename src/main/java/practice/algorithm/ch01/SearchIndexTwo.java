package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*

탐색을 구현할 때에는 항상 다음의 사항을 고려한다

 - 탐색 대상이 중복되는 경우
 - 탐색 대상이 존재하지 않는 경우
 - 인덱스의 기준이 0-based 가 아닌 경우


민규와 주명이는 대한민국의 국민입니다.
국민으로서 부여받은 병역의 의무를 이행하기 위하여 둘은 동반 입대를 결정했습니다.
하지만 동반 입대를 단순히 동시에 입영 신청을 하는 것으로 착각한 둘은 같은 시각에 입영 신청을 하고 입대를 기다리고 있었습니다.
하지만 서로 다른 곳에서 복무를 하게 된다는 사실을 알게 된 둘은 경악했습니다.
또한 비슷한 시기에 입영 신청을 한 같은 과 친구들도 모두 뿔뿔이 흩어져 다른 부대로 가게 되었다는 사실을 알게 되었습니다.
결국 외로움을 참지 못한 주명이는 병무청을 해킹하여 복무지를 조작하기로 결정하였습니다.
주명이는 최근 복무지가 결정된 데이터 N개를 처리된 시간 순서대로 얻을 수 있었습니다.
주명이는 같은 학교 친구들이 모두 같은 복무지에 갈 수 있도록 하고자 합니다.
하지만 일일이 데이터를 확인할 수 없던 주명이는 처음 처리된 아주대학교 학생부터 가장 마지막에 처리된 아주대학교 학생 까지의 정보만을 같은 복무지로 수정하고자 합니다.
주명이가 얻은 복무지 처리 데이터의 수 N과 그 데이터들이 순서대로 주어질 때,
같은 복무지로 처리해야 할 첫 번째 데이터의 번호와 마지막 데이터의 번호를 구하는 프로그램을 작성해주세요.

*/
public class SearchIndexTwo {

    public static final String targetUniv = "AJOU";

    public static void main(String[] args) throws IOException {
        // 1 based index
        // return biggest index
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int listNum = Integer.parseInt(br.readLine());
        List<Integer> indexes = new ArrayList<>();
        List<String> univs = new ArrayList<>();
        for (int i = 0; i < listNum; i++) {
            univs.add(br.readLine());
        }
        for (int j = 0; j < univs.size(); j++) {
            if (targetUniv.equals(univs.get(j))) {
                indexes.add(j);
            }
        }
        int first = indexes.get(0)+1;
        int last = indexes.get(indexes.size()-1)+1;
        System.out.println(first + " " + last);
    }
}
