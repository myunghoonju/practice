package practice.algorithm;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/84512
public class Programmers84512 {
  public int solution(String word) {
    int answer = 0;
    List<String> words = new ArrayList<>();
    sol(words, "");

    return words.indexOf(word);
  }

  private void sol(List<String> data, String word) {
    data.add(word);
    if (word.length() == 5) {
      return;
    }

    String[] alphabets = "AEIOU".split("");
    for (String alphabet : alphabets) {
      sol(data,word + alphabet);
    }
  }
}
