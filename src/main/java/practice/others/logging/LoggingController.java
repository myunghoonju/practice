package practice.others.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.cache.UserCacheWrapper;
import practice.others.cache.domain.model.Agency;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final UserCacheWrapper userCacheWrapper;

    @GetMapping("/log")
    public ResponseEntity<String> log() {
        userCacheWrapper.setAgencyCache();
        String requestId = MDC.get("REQUEST_ID").toString();
        userCacheWrapper.putUserToLocalCache(requestId, requestId);

        Object userA = userCacheWrapper.getUserA(requestId);
        Agency test = userCacheWrapper.getAgency("TEST");
        log.info("userA {}", userA.toString());
        log.info("test {} base {} header {}", test.getAgencyCd(), test.getAgyUrlData().get("base"), test.getAgyHeaderData());

        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
