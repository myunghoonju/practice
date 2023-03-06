package practice.others.cache.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.others.cache.domain.model.AgencyDto;
import practice.others.secret.encryption.CipherUtil;

import javax.persistence.EntityManager;
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
    public void encrpytSaving(int id, AgencyDto agencyDto) throws Exception {
        AgencyInfo entity = AgencyInfo.builder()
                .agencyCd("test" + "-"  + id + "-" + agencyDto.getInformation())
                .information(agencyDto.getEncInfo())
                .build();

        repository.save(entity);
    }

    @Transactional
    public void reEncryptSaving() throws Exception {
        List<AgencyInfo> all = repository.findAll();
        for (AgencyInfo entity : all) {
            String info = CipherUtil.desDecrypt(entity.getInformation());
            entity.setInformation(CipherUtil.aesEncrypt(info));
            log.info("id {}, info {}", entity.getAgencyCd(), info);
        }
    }
}
