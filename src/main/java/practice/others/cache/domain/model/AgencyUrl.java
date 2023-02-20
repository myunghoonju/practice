package practice.others.cache.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgencyUrl {

    private String base;
    private String deposit;
    private String fee;
    private String request;
    private String update;
    private String cancel;
}
