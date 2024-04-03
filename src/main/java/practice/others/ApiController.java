package practice.others;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import practice.others.multipleDb.domain.Cactus;
import practice.others.multipleDb.domain.CactusRepository;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CactusRepository cactusRepository;

    @GetMapping("/yesterday")
    public String test(@RequestParam("date")
                       @DateTimeFormat(pattern = "yyyy-MM-dd")
                       LocalDate localDate) {
        return localDate.minusDays(1L).toString();
    }

    @GetMapping("/magic/cactus")
    public String cactus() {
        String name = UUID.randomUUID().toString();
        cactusRepository.save(new Cactus(name, ThreadLocalRandom.current().nextInt(), LocalDateTime.now()));
        return name;
    }
}
