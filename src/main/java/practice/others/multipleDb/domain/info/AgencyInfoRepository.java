package practice.others.multipleDb.domain.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgencyInfoRepository extends JpaRepository<AgencyInformation, Long> {

    AgencyInformation findByAgencyCd(String agencyCd);
    List<AgencyInformation> findByCreatedDateGreaterThanAndModifiedDateLessThan(LocalDateTime a, LocalDateTime b);
}
