package practice.others.cache.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import practice.others.cache.domain.time.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@Table(name = "AGENCY_INFO")
@Entity
public class AgencyInfo extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(100) not null comment 'agency identifier'")
    private String agencyCd;
    @Column(columnDefinition = "varchar(1000) null")
    private String information;

    @Builder
    public AgencyInfo(String agencyCd, String information) {
        this.agencyCd = agencyCd;
        this.information = information;
    }
}
