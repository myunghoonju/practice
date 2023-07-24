package practice.others.enumvalues;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class EnumValueGoesHere {

    private String key;
    private String val;

    public static void main(String[] args) {
        List<EnumValueGoesHere> list = Element.getList();
        for (EnumValueGoesHere e : list) {
            e.setKey("1");
            log.info("key {}, val {}", e.getKey(), e.getVal());
        }
    }
}
