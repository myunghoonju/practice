package practice.algorithm.prog;

import java.util.Arrays;

public class Q49993 {

  public int sol(String skill, String[] skillTree) {
    return (int) Arrays.stream(skillTree)
                       .map(s -> s.replaceAll("[^" + skill + "]", ""))
                       .filter(skill::startsWith)
                       .count();
  }

  public static void main(String[] args) {
    Q49993 sol = new Q49993();
    String skill = "CBD";
    String[] tree = {"BACDE", "CBADF", "AECB", "BDA"};

    System.out.println(sol.sol(skill, tree));
  }
}
