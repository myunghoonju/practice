package practice.algorithm.prog;

public class Q42842 {

    public int[] solution(int brown, int yellow) {
        for (int width = 3; width < 5_000; width++) {
            for (int height = 3; height <= width; height++) {
                int b = (width + height - 2) * 2;
                int y = (width * height) - b;
                if (b == brown && y == yellow) {
                    return new int[] {width, height};
                }
            }

        }
        return null;
    }

    public static void main(String[] args) {
        for (int i : new Q42842().solution(10, 2)) {
            System.out.println(i);
        }
    }
}
