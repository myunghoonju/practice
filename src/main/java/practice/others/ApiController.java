package practice.others;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.others.redis.TemplateService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ApiController {

//  private final TemplateService service;
//
//    @GetMapping("/yesterday")
//    public String test(@RequestParam("date")
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")
//                       LocalDate localDate) {
//      return localDate.minusDays(1L) +
//             " with redis test result: " +
//             service.test("test", "value");
//    }
}
