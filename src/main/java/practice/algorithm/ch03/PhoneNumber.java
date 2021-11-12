package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 용돈이 필요해진 지수는 한 설문조사 기관에서 아르바이트를 하게 되었다.
 지수는 수 많은 사람들에게 전화를 통해 설문조사를 하는 일을 맡게 되었는데,
 어느 날 휴식시간에 문득 사람들이 전화번호 뒷자리로 가장 많이 사용하는 번호는 무엇인지 궁금해졌다.
 하지만 지수는 보유한 전화번호가 너무 많고, 전화번호의 뒷자리는 0000~9999의 총 1만가지의 종류가 있기에 눈으로 세는 것은 도저히 불가능할 것이라는 판단을 내렸다.
 지수를 위하여 사람들의 전화번호 뒷자리 중 가장 많이 사용되는 번호를 찾아주자.
*/
public class PhoneNumber {

    public static final int MAX_TABLE_LENGTH = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int range = Integer.parseInt(br.readLine());
        int[] data = new int[range];

        for (int i = 0; i < range; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        getFrequentNumber(data, range);
    }

    /*
    주어진 전화번호 뒷자리들 중 가장 많이 등장한 번호를 출력한다. 0을 포함하여 공백없는 네 자리 숫자로 출력해야한다.
    단, 등장한 횟수가 같은 번호가 두 개 이상인 경우 가장 사전순으로 빠른 숫자를 출력한다.
    */
    private static void getFrequentNumber(int[] data, int range) {
        int freq_number = 0;
        int[] table = new int[MAX_TABLE_LENGTH];
        fillTable(data, range, table);

        for (int j = 1000; j <= 9999; j++) {
            if (table[j] > table[freq_number]) { // 빈도가 동일한 경우, 사전순으로 앞선 숫자만 들어간다.
                freq_number = j;
            }
        }

        System.out.println(freq_number);
    }

    private static void fillTable(int[] data, int range, int[] table) {
        // reset table
        Arrays.fill(table, 0);

        // 9999나오면 9999에 1 > 2 > 3 ...  ::
        // e.g.., 1234, 1234, 2345 >>> table[1234] = 2, table[2345] = 1
        for (int k = 0; k < range; k++) {
            int index = data[k];
            table[index] += 1;
        }
    }
}
