package practice.others.multipleDb.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Agency {

    private String agencyCd;
    private Map<String, String> agyHeaderData;
    private Map<String, String> agyUrlData;

    public static ObjectMapper mapper = new ObjectMapper();
}
