package practice.others.cache.domain.agencyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgencyInfoRepository extends JpaRepository<AgencyInfo, Long> {

    AgencyInfo findByAgencyCd(String agencyCd);
    List<AgencyInfo> findByCreatedDateGreaterThanAndModifiedDateLessThan(LocalDateTime a, LocalDateTime b);
}
