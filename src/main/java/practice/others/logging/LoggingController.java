package practice.others.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.cache.AgencyInfoService;
import practice.others.cache.domain.model.AgencyDto;
import practice.others.secret.TokenGenerator;
import practice.others.secret.okhttp.RetrofitService;
import practice.others.secret.okhttp.UserApiService;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final AgencyInfoService service;

    @GetMapping("/log")
    public ResponseEntity<String> log() throws Exception {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString();
            AgencyDto dto = AgencyDto.builder()
                    .information(uuid)
                    .build();
            service.encryptSaving(i, dto);
        }
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @GetMapping("/log2")
    public ResponseEntity<String> log2() throws Exception {
        service.reEncryptSaving();
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @GetMapping("/mock")
    public ResponseEntity<Object> mockApi() throws Exception {
        String token = TokenGenerator.getToken();
        HashMap<String, String> header = new HashMap<>();
        header.put("authorization", token);

        UserApiService userApiService = RetrofitService.getCli("http://httpstat.us/401/")
                                                       .create(UserApiService.class);
        Object body = userApiService.getUserPost(header, 1)
                                    .execute()
                                    .body();
        return ResponseEntity.ok().body(body);
    }
}
