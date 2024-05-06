package practice.others.thread;

import java.util.ArrayList;

// -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {

    static class OOMObject {}

    public static void main(String[] args) {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        while (true) {
            oomObjects.add(new OOMObject());
        }
    }
}
