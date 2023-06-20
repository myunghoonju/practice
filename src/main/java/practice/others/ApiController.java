package practice.others;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.others.cache.AgencyInfoService;
import practice.others.cache.model.AgencyInfoDto;
import practice.others.cache.model.TestModel;
import practice.others.cache.model.UserCache;
import practice.others.multipleDb.domain.info.AgencyInfo;
import practice.others.secret.TokenGenerator;
import practice.others.secret.okhttp.RetrofitService;
import practice.others.secret.okhttp.MockApiService;
import retrofit2.Response;

import java.util.HashMap;

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

    @PostMapping("/caching/user")
    public ResponseEntity<UserCache> putUserACache(@RequestBody UserCache content) {
        service.putUserCache(content);
        return ResponseEntity.ok().body(content);
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

    @GetMapping("/mock/test")
    public ResponseEntity<String> mockApi() throws Exception {
        String token = TokenGenerator.getToken();
        HashMap<String, String> header = new HashMap<>();
        header.put("authorization", token);

        TestModel testModel = TestModel.builder().key("key").build();
        MockApiService mockApiService = RetrofitService.getCli("http://localhost:8085")
                                                       .create(MockApiService.class);

        Response<TestModel> execute = mockApiService.mockPost(header, testModel).execute();
        return ResponseEntity.ok().body("body");
    }
}
