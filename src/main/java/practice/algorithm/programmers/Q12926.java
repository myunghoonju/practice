package practice.algorithm.programmers;

public class Q12926 {

    public static String solution(String s, int n) {
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray()) {
            b.append(push(c, n));

        }

        return b.toString();
    }

    private static char push(char c, int n) {
        if (!Character.isAlphabetic(c)) {
            return c;
        }

        int offset = Character.isUpperCase(c) ? 'A' : 'a';
        int at = c - offset;
        int p = 'Z' - 'A' + 1;
        at = (at + n) % p;

        return (char) (at + offset);
    }

    public static void main(String[] args) {
        System.out.println(solution("AB", 3));
    }
}
