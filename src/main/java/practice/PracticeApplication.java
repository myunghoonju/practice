package practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PracticeApplication.class, args);
    }
}