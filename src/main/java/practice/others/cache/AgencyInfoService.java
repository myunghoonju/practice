package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.others.cache.domain.OtherColumns;
import practice.others.cache.domain.agencyInfo.AgencyInfo;
import practice.others.cache.domain.agencyInfo.AgencyInfoRepository;
import practice.others.cache.domain.model.AgencyDto;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class AgencyInfoService {

    private final AgencyInfoRepository repository;
    private final EntityManager entityManager;

    public AgencyInfoService(AgencyInfoRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Transactional
    public void encryptSaving(int id, AgencyDto agencyDto) {
        AgencyInfo entity = AgencyInfo.builder()
                .agencyCd("test" + "-"  + id + "-" + agencyDto.getInformation())
                .information(agencyDto.getInformation())
                .build();

        repository.save(entity);
    }

    @Transactional
    public void reEncryptSaving() throws Exception {
        LocalDateTime created = LocalDateTime.of(2023, Month.MARCH, 3, 17, 20, 0);
        LocalDateTime modified = LocalDateTime.of(2023, Month.DECEMBER, 30, 17, 20, 0);
        List<AgencyInfo> all = repository.findByCreatedDateGreaterThanAndModifiedDateLessThan(created, modified);

        for (AgencyInfo entity : all) {
            log.info("all cnt {}", all.size());
            entity.setAgencyCd("a-" + UUID.randomUUID());
            entity.setInformation(UUID.randomUUID().toString());

            OtherColumns otherColumns = OtherColumns.builder()
                                                    .otherColumn("o-" + UUID.randomUUID())
                                                    .build();
            entity.setOtherColumns(otherColumns);
        }
    }
}
