package practice.reference;

public class MethodVarargs {
    public static void main(String[] args) {
        MethodVarargs varargs = new MethodVarargs();

        varargs.calcNums(1);
        varargs.calcNums(1,2);
        varargs.calcNums(1,2,3);
        varargs.calcNums(1,2,3,4);
    }

    public void calcNums(int...numbers) {
        int total = 0;
        for (int num:numbers) {
            total+=num;
        }

        System.out.println(total);
    }
}
