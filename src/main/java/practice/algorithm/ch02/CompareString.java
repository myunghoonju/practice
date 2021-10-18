package practice.algorithm.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareString {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        MyString firstMyStr = new MyString(first);
        MyString secondMyStr = new MyString(second);

        System.out.println(firstMyStr.compareTo(secondMyStr));
    }
}

class MyString implements Comparable<MyString> {
    private char[] characters;

    public MyString(String original) {
        characters = original.toCharArray();
    }

    public MyString(char[] original) {
        characters = new char[original.length];
        System.arraycopy(original, 0, characters, 0, original.length);
    }

    /**
     * this와 파라미터 o를 비교하여
     *   - this가 사전순으로 앞서면 음수
     *   - o가 사전순으로 앞서면 양수
     *   - 동일만 문자열이면 0
     *  을 반환하는 함수를 설계하시오
     * @param o
     * @return
     */
    @Override
    public int compareTo(MyString o) {
        int n = Math.min(this.characters.length, o.characters.length);
        boolean isSame = this.equals(o);

        if (!isSame) {
            for (int i = 0; i < n; i++) {
                if (this.characters[i] < o.characters[i]) {
                    return -1;
                }
                if (this.characters[i] > o.characters[i]) {
                    return 1;
                }
            }

            if (this.characters.length < o.characters.length) {
                return -1;
            } else {
                return 1;
            }
        }

        return 0;
    }

    /**
     * 두 문자열이 일치하면 true, 아니면 false를 반환하는 함수를 작성하시오.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MyString)) {
            return false;
        }
        MyString o = (MyString)obj;
        int index = 0;

        if (this.characters.length != o.characters.length) {
            return false;
        }

        for (char ch : this.characters) {
            if (ch != o.characters[index++]) {
                return false;
            }
        }

        return true;
    }
}