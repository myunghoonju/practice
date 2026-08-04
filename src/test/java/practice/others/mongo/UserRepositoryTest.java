package practice.others.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @AfterEach
  void cleanUp() {
    userRepository.deleteAll();
  }

  @Test
  void savesAndFindsUserInAtlas() {
    userRepository.save(new User("myunghoon", 30));

    User found = userRepository.findByUsername("myunghoon");

    assertThat(found).isNotNull();
    assertThat(found.getAge()).isEqualTo(30);
  }
}
