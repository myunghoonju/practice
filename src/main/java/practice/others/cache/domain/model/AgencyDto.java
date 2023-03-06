package practice.others.cache.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.others.secret.encryption.CipherUtil;

@Getter
@Setter
@NoArgsConstructor
public class AgencyDto {

    private String information;

    @Builder
    public AgencyDto(String information) {
        this.information = information;
    }

    public String getEncInfo() throws Exception {
        return CipherUtil.desEncrypt(information);
    }
}
