package practice.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 32비트 int형 정수가 N개 주어진다. 이 때 N개의 정수 중 가장 큰 값을 출력하는 프로그램을 작성하시오.
public class GetMax {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> n = new ArrayList<>();
		int numSize = Integer.parseInt(br.readLine());
		String[] brArr = br.readLine().split(" ");
		for (int i = 0; i < numSize; i++) {
			n.add(Integer.parseInt(brArr[i]));
		}
		getMax(numSize, n);
	}

	private static void getMax(int numSize, List<Integer> n) {
		int maxValue = n.get(0);

		for (int i = 0; i < numSize; i++) {
			int value= n.get(i);
			if (maxValue < value) {
				maxValue = value;
			}
		}
		System.out.println(maxValue);
	}
}
