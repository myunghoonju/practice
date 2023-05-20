package practice.others;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.others.cache.AgencyInfoService;
import practice.others.cache.model.AgencyInfoDto;
import practice.others.multipleDb.domain.info.AgencyInfo;
import practice.others.multipleDb.domain.model.AgencyDto;
import practice.others.secret.TokenGenerator;
import practice.others.secret.okhttp.RetrofitService;
import practice.others.secret.okhttp.UserApiService;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AgencyInfoService service;

    @PostMapping("/info")
    public ResponseEntity<String> saveInfo(@RequestBody AgencyInfoDto dto) throws Exception {
        String agyCd = service.save(dto);
        return ResponseEntity.ok().body(agyCd);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo(@RequestParam String agyCd) {
        AgencyInfo agencyInfo = service.get(agyCd);
        return ResponseEntity.ok().body(agencyInfo.toString());
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
