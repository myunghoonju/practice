package practice.others.sort;

import java.util.stream.Stream;

public enum Operate {

    SALARY("SALARY") {
        @Override
        public int compare(Target left, Target right) {
            return Long.compare(left.getSalary(), right.getSalary());
        }
    },

    NAME("NAME") {
        @Override
        public int compare(Target left, Target right) {
            return left.getName().compareTo(right.getName());
        }
    };

    private final String name;

    Operate(String name) {
        this.name = name;
    }

    public static Operate getOpt(String opt) {
        return Stream.of(values())
                     .filter(val -> val.name.equals(opt))
                     .findFirst()
                     .orElseGet(Operate::getNameOpt);
    }

    public static Operate getNameOpt() {
        return NAME;
    }

    public int compare(Target left, Target right) { return 0; }
}
