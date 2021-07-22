package practice.reference;

public class ReferencePass {
    public static void main(String[] args) {
        ReferencePass ref = new ReferencePass();
        ref.callPassByValue();
    }

    public void callPassByValue() {
        int a = 10;
        String b = "b";
        System.out.println("before");
        System.out.println(a);
        System.out.println(b);

        passByValue(a, b);
        System.out.println("after");
        System.out.println(a);
        System.out.println(b);

    }

    public void passByValue(int a, String b) {
        a = 20;
        b = "c";
        System.out.println("in");
        System.out.println(a);
        System.out.println(b);

    }

/*
Result:
    before
    10
    b
    after
    20
    c
    re
    10
    b
*/
}
