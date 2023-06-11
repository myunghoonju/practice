package practice.others.cache.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserCache {

    private String key;
    private String val;

    @Builder
    public UserCache(String key, String val) {
        this.key = key;
        this.val = val;
    }
}
