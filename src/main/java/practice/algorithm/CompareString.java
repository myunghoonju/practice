package practice.algorithm;

public class CompareString {

    public static void main(String[] args) {
        char[] s1 = "app".toCharArray();
        char[] s2 = "all".toCharArray();

        System.out.printf("compare() Result : %d\n", compare(s1,s2) );
        System.out.printf("equals() Result : %b\n", equals(s1,s2) );
    }

    public static int compare(char[] s1, char[] s2){
		/* 두 문자열 s1, s2를 비교하여 일치하면 0,
		s1이 사전순으로 앞서면 음수
		s2가 사전순으로 앞서면 양수를 반환하는 함수
		*/

        // 1. 서로 다른 문자가 등장하는 위치가 존재
        int n = Math.min(s1.length, s2.length);
        for (int i = 0; i < n; i++) {
            if (s1[i] != s2[i]) {
                return (s1[i] - s2[i]);
            }
        }

        // 2. 서로 다른 문자가 등장하지 않는 경우(길이가 같은경우, 다른 경우)
        // a. 둘이 길이가 같다? 전체 문자가 일치했다 -> 이 경우는 문자열이 일치
        return s1.length - s2.length;
    }

    public static boolean equals(char[] s1, char[] s2){
		/* 두 문자열 s1, s2가 일치하면 true
		 일치하지 않으면 false를 반환하는 함수
		*/
        if (s1.length != s2.length) {
            return false;
        }

        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }
}
