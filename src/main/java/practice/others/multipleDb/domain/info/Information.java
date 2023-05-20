package practice.others.multipleDb.domain.info;


import lombok.*;

import java.util.Map;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Information {

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

    @Builder
    public Information(Url url,
                       Map<String, Object> header) {
        this.url = url;
        this.header = header;
    }
}
