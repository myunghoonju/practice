package practice.algorithm;

import java.util.Arrays;

public class RomanInteger {

  public int romanToInt(String s) {
    int sum = 0;
    int index = 0;
    while (index < s.length()) {
      if (index < s.length() -1) {
        String doubleSymbol = s.substring(index, index + 2);
        if (SYMBOLS.find(doubleSymbol) != null) {
          sum += SYMBOLS.find(doubleSymbol).getValue();
          index += 2;
          continue;
        }
      }

      String symbol = s.substring(index, index + 1);
      if (SYMBOLS.find(symbol) != null) {
        sum += SYMBOLS.find(symbol).getValue();
        index += 1;
      }
    }
    return sum;
  }

  enum SYMBOLS {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000),
    IV(4),
    IX(9),
    XL(40),
    XC(90),
    CD(400),
    CM(900)
    ;
    private final int value;

    SYMBOLS(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static SYMBOLS find(String symbol) {
      return Arrays.stream(SYMBOLS.values()).filter(s -> s.name().equals(symbol)).findFirst().orElse(null);
    }
  }

  public static void main(String[] args) {
    System.out.println(new RomanInteger().romanToInt("MCMXCIV"));
  }
}
