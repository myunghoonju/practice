package practice.reference;

public class StaticBlock {

    static int data = 1;

    public StaticBlock() {
        System.out.println("StaticBlock constructor");
    }

    static {
        System.out.println("first static");
        data = 3;
    }

    static  {
        System.out.println("second static");
        data = 5;
    }

    public static int getData() {
        return data;
    }

    public static void main(String[] args) {
        StaticBlock check = new StaticBlock();
        check.makeStaticBlockObj();
    }

    public void makeStaticBlockObj() {
        System.out.println(StaticBlock.getData()+1);
        System.out.println("create block 1");
        StaticBlock block1 = new StaticBlock();
        System.out.println("created block 1");
        System.out.println("---------");
        System.out.println("create  block 2");
        StaticBlock block2 = new StaticBlock();
        System.out.println("created block 2");
        System.out.println(StaticBlock.getData());
    }
}
