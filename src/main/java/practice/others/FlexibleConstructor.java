package practice.others;

public class FlexibleConstructor {

  public static void main(String[] args) {
    try {
      new NonFlexibleConstructor("");
    } catch (Exception e) {
      System.out.println("-- " + e.getMessage());
    }

  }
}

class BaseClass {
  public BaseClass() {
    check();
  }

  public void check() {}
}


class NonFlexibleConstructor extends BaseClass {

  private String value;

  public NonFlexibleConstructor(String aValue) {
// call to 'super()' must be first statement in constructor body
//  super();
    this.value = aValue;
  }

  // not runtime but null pointer
  public void check() {
    if (value.length() == 0) {
      throw new RuntimeException("invalid value");
    }
  }
}
