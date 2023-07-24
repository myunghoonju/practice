package practice.others.enumvalues;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Element {

    A("1", "AA"),
    B("2", "BB"),
    C("3", "CC");

    public final String number;
    public final String alphabet;

    private static final List<EnumValueGoesHere> LIST_OF_CLASS = new ArrayList<>();

    static {
        for (Element e : values()) {
            EnumValueGoesHere enumValueGoesHere = new EnumValueGoesHere();
            enumValueGoesHere.setKey(e.getNumber());
            enumValueGoesHere.setVal(e.getAlphabet());
            LIST_OF_CLASS.add(enumValueGoesHere);
        }
    }

    Element(String number, String alphabet) {
        this.number = number;
        this.alphabet = alphabet;
    }

    public static List<EnumValueGoesHere> getList() {
        return LIST_OF_CLASS;
    }
}
