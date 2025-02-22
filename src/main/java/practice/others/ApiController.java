package practice.others;

import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.secret.okhttp.MybootApiService;
import practice.others.secret.okhttp.RetrofitService;
import retrofit2.Call;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("/test")
    public String test() {
      Call<ResponseBody> push = RetrofitService.getCli("http://update.juso.go.kr")
                                               .create(MybootApiService.class)
                                               .push("U01TX0FVVEgyMDI1MDIyMDIzNDAwNDExNTQ5MDU=",
                                                     "D",
                                                     "100001",
                                                     "N",
                                                     "20250220",
                                                     "20250220");
      try(ResponseBody responseBody = push.execute().body()) {
        if (responseBody != null) {
          new BufferedReader(new InputStreamReader(responseBody.byteStream())).lines()
                                                                              .forEach(System.err::println);
        }
        return "";
      } catch (Exception e) {
        return e.getMessage();
      }
    }
}
