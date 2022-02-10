package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReserveSeat {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] digits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int range = nk[0];
        int winners = nk[1];

        System.out.println(sol(range, winners, digits));
    }

    private static int sol(int range, int winners, int[] digits) {
        int sol = 0;
        int MAX = 1_000_000;
        FreqTable freqTable = new FreqTable(0, new int[MAX]);

        for (int i = 0; i < range-1 ; i++) {
            freqTable.addDigits(digits[i]);
        }

        for (int j = 0; (j + range -1) < range; j++) {
            int left = j;
            int right = (j + range - 1);

            freqTable.addDigits(digits[right]);
            if (left > 0) {
                freqTable.removeDigits(digits[left -1]);
            }
            if (freqTable.uniqueDigits == winners) {
                sol++;
            }
        }


        return sol;
    }

    static class FreqTable {
        private int uniqueDigits;
        private int[] digitCount;

        public FreqTable(int uniqueDigits, int[] digitCount) {
            this.uniqueDigits = uniqueDigits;
            this.digitCount = digitCount;
        }

        void addDigits(int digit) {
            int freq = digitCount[digit];
            if (freq == 0) {
                this.uniqueDigits++;
            } else if (freq == 1) {
                this.uniqueDigits--;
            }

            this.digitCount[digit]++;
        }

        public void removeDigits(int digit) {
            int count = digitCount[digit];
            if (count == 1) {
                this.uniqueDigits--;
            } else if (count == 2) {
                this.uniqueDigits++;
            }

            this.digitCount[digit]--;
        }
    }
}
