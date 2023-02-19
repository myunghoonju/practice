package practice.others.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.cache.UserCacheWrapper;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final UserCacheWrapper userCacheWrapper;

    @GetMapping("/log")
    public ResponseEntity<String> log() {
        String requestId = MDC.get("REQUEST_ID").toString();

        userCacheWrapper.putUserToLocalCache(requestId, requestId);

        Object user = userCacheWrapper.getUser(requestId);
        log.info("user {}", user.toString());

        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }
}
