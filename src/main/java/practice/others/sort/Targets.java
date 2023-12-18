package practice.others.sort;

import java.util.List;

public class Targets {

    public static List<Target> sortBySal(List<Target> targets) {
        targets.sort(Target::compareSalary);
        return targets;
    }

    public static List<Target> sortByName(List<Target> targets) {
        targets.sort(Target::alphabetic);

        return targets;
    }

    public static List<Target> sort(String opt, List<Target> targets) {
        targets.sort(Operate.getOpt(opt)::compare);
        return targets;
    }
}
