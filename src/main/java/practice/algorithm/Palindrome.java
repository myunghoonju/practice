package practice.algorithm;

public class Palindrome {

  public static void main(String[] args) {
    boolean palindrome = palindrome("a.");
    System.out.println(palindrome);
  }

  public static boolean palindrome(String s) {
    String lowerCase = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    int left = 0;
    int right = lowerCase.length() - 1;

    while (left < right) {
      if (lowerCase.charAt(left) != lowerCase.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }


    return true;
  }
}