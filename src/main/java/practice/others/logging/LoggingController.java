package practice.others.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.cache.UserCacheWrapper;
import practice.others.cache.domain.model.Agency;
import practice.others.secret.TokenGenerator;
import practice.others.secret.okhttp.RetrofitService;
import practice.others.secret.okhttp.UserApiService;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final UserCacheWrapper userCacheWrapper;

    @GetMapping("/log")
    public ResponseEntity<String> log() {
        userCacheWrapper.setAgencyCache();
        String requestId = MDC.get("REQUEST_ID");
        userCacheWrapper.putUserToLocalCache(requestId, requestId);

        Object userA = userCacheWrapper.getUserA(requestId);
        Agency test = userCacheWrapper.getAgency("TEST");
        log.info("userA {}", userA.toString());
        log.info("test {} base {} header {}", test.getAgencyCd(), test.getAgyUrlData().get("base"), test.getAgyHeaderData());

        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @GetMapping("/mock")
    public ResponseEntity<Object> mockApi() throws Exception {
        String token = TokenGenerator.getToken();
        HashMap<String, String> header = new HashMap<>();
        header.put("authorization", token);

        UserApiService userApiService = RetrofitService.getCli()
                                                       .create(UserApiService.class);
        Object body = userApiService.getUserPost(header, 1)
                                    .execute()
                                    .body();
        return ResponseEntity.ok().body(body);
    }
}
