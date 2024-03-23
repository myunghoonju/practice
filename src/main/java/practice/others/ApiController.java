package practice.others;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/yesterday")
    public String test(@RequestParam("date")
                       @DateTimeFormat(pattern = "yyyy-MM-dd")
                       LocalDate localDate) {
        return localDate.minusDays(1L).toString();
    }
}
