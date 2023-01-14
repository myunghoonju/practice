package practice.others.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggingController {

    @GetMapping("/log")
    public ResponseEntity<String> log() {
        log.info("aaa {}", MDC.get("requestId"));
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

}
