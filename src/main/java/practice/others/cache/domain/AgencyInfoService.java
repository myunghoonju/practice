package practice.others.cache.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.others.cache.domain.model.AgencyDto;
import practice.others.cache.domain.model.UpdateAgencyInfoDto;
import practice.others.secret.encryption.CipherUtil;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;


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
    public void encryptSaving(int id, AgencyDto agencyDto) throws Exception {
        AgencyInfo entity = AgencyInfo.builder()
                .agencyCd("test" + "-"  + id + "-" + agencyDto.getInformation())
                .information(agencyDto.getEncInfo())
                .build();

        repository.save(entity);
    }

    @Transactional
    public void reEncryptSaving() throws Exception {
        LocalDateTime created = LocalDateTime.of(2023, Month.MARCH, 3, 17, 20, 0);
        LocalDateTime modified = LocalDateTime.of(2023, Month.MARCH, 9, 17, 20, 0);
        List<AgencyInfo> all = repository.findByCreatedDateGreaterThanAndModifiedDateLessThan(created, modified);
        log.info("all cnt {}", all.size());
        for (AgencyInfo entity : all) {
            String info = CipherUtil.desDecrypt(entity.getInformation());
            entity.setInformation(CipherUtil.aesEncrypt(info));
            log.info("id {}, info {}", entity.getAgencyCd(), info);
        }
    }
}
