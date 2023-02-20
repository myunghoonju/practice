package practice.others.cache.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "AGENCY_INFO")
@Entity
public class AgencyInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(100) not null comment 'agency identifier'")
    private String agencyCd;
    @Column(columnDefinition = "varchar(1000) null")
    private String information;

    public void save(String agencyCd, String information) {
        this.agencyCd = agencyCd;
        this.information = information;
    }
}
