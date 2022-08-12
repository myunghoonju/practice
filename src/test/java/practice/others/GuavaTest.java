package practice.others;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class GuavaTest {

    @Test
    public void unmodifiableMap_throws_exception_when_trying_to_modify_it() {
        //given
        HashMap<String, String> mutableMap = new HashMap<>();
        mutableMap.put("USA", "North America");
        //when
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
        //then
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableMap.put("Canada", "North America"));
    }

    @Test
    public void mutableMap_modifications_reflected_in_the_unmodifiableMap() {
        //given
        HashMap<String, String> mutableMap = new HashMap<>();
        mutableMap.put("USA", "North America");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
        //when
        mutableMap.put("Canada", "North America");
        //then
        assertThat(unmodifiableMap.containsKey("Canada")).isTrue();
    }


    @Test
    public void Guava_copyOf_test() {
        //given
        HashMap<String, String> mutableMap = new HashMap<>();
        mutableMap.put("USA", "North America");
        //when
        ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(mutableMap);
        mutableMap.remove("USA");
        mutableMap.put("Mexico", "North America");
        //then
        assertThat(immutableMap.containsKey("USA")).isTrue();
        assertThat(immutableMap.containsKey("Mexico")).isFalse();
        assertThrows(UnsupportedOperationException.class, () -> immutableMap.put("Canada", "North America"));

    }
}
