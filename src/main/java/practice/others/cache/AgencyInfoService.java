package practice.others.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.others.cache.model.AgencyInfoDto;
import practice.others.multipleDb.domain.OtherColumns;
import practice.others.multipleDb.domain.info.AgencyInfo;
import practice.others.multipleDb.domain.info.AgencyInfoRepository;
import practice.others.multipleDb.domain.info.Information;
import practice.others.multipleDb.domain.model.AgencyDto;

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

    private final ObjectMapper mapper = new ObjectMapper();

    public AgencyInfoService(AgencyInfoRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public String save(AgencyInfoDto dto) {
        AgencyInfo entity = AgencyInfoDto.toEntity(dto);
        repository.save(entity);

        return entity.getAgencyCd();
    }

    public AgencyInfo get(String agyCd) {
        return repository.findByAgencyCd(agyCd);
    }

    @Transactional
    public void encryptSaving(int id, AgencyDto agencyDto) throws Exception {
        Information information = Information.builder().build();
        AgencyInfo entity = AgencyInfo.builder()
                .agencyCd("test" + "-"  + id + "-" + agencyDto.getInformation())
                .information(information)
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
            entity.setInformation(null);

            OtherColumns otherColumns = OtherColumns.builder()
                                                    .otherColumn("o-" + UUID.randomUUID())
                                                    .build();
            entity.setOtherColumns(otherColumns);
        }
    }
}
