package practice.others.cache.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyInfoRepository extends JpaRepository<AgencyInfo, Long> {

    AgencyInfo findByAgencyCd(String agencyCd);
}
