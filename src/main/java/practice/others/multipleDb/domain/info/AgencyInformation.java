package practice.others.multipleDb.domain.info;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import practice.others.cache.Converters;
import practice.others.multipleDb.domain.OtherColumns;
import practice.others.multipleDb.domain.time.BaseTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AGENCY_INFO")
@Entity
public class AgencyInformation extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AGENCY_CD", columnDefinition = "VARCHAR(100) NOT NULL COMMENT 'agency identifier'")
    private String agencyCd;

    @Convert(converter = Converters.JsonConverter.class)
    @Column(name = "INFORMATION", columnDefinition = "JSON")
    private Information information;

    @Embedded
    @Convert(converter = Converters.OtherConverter.class, attributeName = "other_column")
    private OtherColumns otherColumns;

    @Builder
    public AgencyInformation(String agencyCd,
                             Information information,
                             OtherColumns otherColumns) {
        this.agencyCd = agencyCd;
        this.information = information;
        this.otherColumns = otherColumns;
    }
}
