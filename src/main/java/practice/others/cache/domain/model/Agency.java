package practice.others.cache.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.others.cache.domain.AgencyInfo;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Agency {

    private String agencyCd;
    private Map<String, String> agyHeaderData;
    private Map<String, String> agyUrlData;

    public static ObjectMapper mapper = new ObjectMapper();

    public void test(AgencyInfo agencyInfo) throws JsonProcessingException {

        String information = agencyInfo.getInformation();
        Map<String, Object> map = mapper.readValue(information, Map.class);
        Object url = map.get("url");
        Object header = map.get("header");

        this.agyUrlData = mapper.convertValue(url, Map.class);
        this.agyHeaderData = mapper.convertValue(header, Map.class);
        this.agencyCd = agencyInfo.getAgencyCd();
    }
}
