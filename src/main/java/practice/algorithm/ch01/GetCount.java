package practice.algorithm.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
	그룹의 리더 역할을 맡고 있는 수정이는 교보문고 한복판에서 사라진 지수와 미주를 찾고 있습니다.
	하지만 교보문고에는 사람이 너무 많아 지수와 미주를 찾기가 너무 힘듭니다.
	초인적인 능력을 가진 수정이는 멀리서도 사람들의 키를 1cm단위로 정확히 알아낼 수 있습니다.
	수정이는 자신의 능력을 활용하여 미주나 지수와 키가 같은 사람만을 쫓아가 확인하기로 하였습니다.
	현재 교보문고에 있는 모든 사람들의 수와 수정이가 인식한 키가 주어집니다.
	주어지는 미주와 지수의 키를 활용하여 수정이는 최대 몇 명의 사람을 확인해야 하는 지 계산하는 프로그램을 작성해주세요.
*/
public class GetCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        String[] rangeData = br.readLine().split(" ");
        List<Integer> n = new ArrayList<>();
        getCount(data, rangeData);
    }

    public static void getCount(String[] data, String[] rangeData) {
        int range = 0;
        int miju = 0;
        int jisu = 0;
        List<Integer> heights = new ArrayList<>();
        int comparisonNumber = 0;

        range = Integer.parseInt(data[0]);
        miju = Integer.parseInt(data[1]);
        jisu = Integer.parseInt(data[2]);




        if (range == rangeData.length) {
            for (String height : rangeData) {
                heights.add(Integer.parseInt(height));
            }
        }

        for (int height : heights) {
            if (miju == height || jisu == height) {
                comparisonNumber++;
            }
        }

        System.out.println(comparisonNumber);
    }
}
