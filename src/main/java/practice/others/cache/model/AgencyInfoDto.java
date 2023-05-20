package practice.others.cache.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.others.multipleDb.domain.info.AgencyInfo;
import practice.others.multipleDb.domain.info.Information;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AgencyInfoDto {

    private Url url;
    private Map<String, Object> header;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Url {
        private String base;
        private String deposit;
        private String fee;
        private String request;
        private String update;
        private String cancel;

        @Builder
        public Url(String base,
                   String deposit,
                   String fee,
                   String request,
                   String update,
                   String cancel) {
            this.base = base;
            this.deposit = deposit;
            this.fee = fee;
            this.request = request;
            this.update = update;
            this.cancel = cancel;
        }
    }

    public static AgencyInfo toEntity(AgencyInfoDto dto) {
        Information.Url url = Information.Url.builder()
                .base(dto.url.getBase())
                .deposit(dto.url.getDeposit())
                .fee(dto.url.getFee())
                .request(dto.url.getRequest())
                .update(dto.url.getUpdate())
                .cancel(dto.url.getCancel())
                .build();
        Information info = Information.builder()
                .url(url)
                .header(dto.getHeader())
                .build();

        return AgencyInfo.builder().agencyCd(UUID.randomUUID().toString())
                .information(info)
                .build();
    }
}
