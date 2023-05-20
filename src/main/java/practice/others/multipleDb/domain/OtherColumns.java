package practice.others.multipleDb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OtherColumns {

    @Column(name = "OTHER_COLUMN", columnDefinition = "varchar(1000) null")
    private String otherColumn;

    @Builder
    public OtherColumns(String otherColumn) {
        this.otherColumn = otherColumn;
    }
}
