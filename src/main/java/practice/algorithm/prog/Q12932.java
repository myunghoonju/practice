package practice.algorithm.prog;

public class Q12932 {

    public static int[] solution(long n) {
        char[] arr = new StringBuilder(Long.toString(n)).reverse().toString().toCharArray();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] - '0';
        }

        return res;
    }

    public static void main(String[] args) {
        int[] solution = solution(12345);
        for (int j : solution) {
            System.out.println(j);
        }
    }
}
