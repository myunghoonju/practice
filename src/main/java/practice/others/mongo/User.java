package practice.others.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {

  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  private int age;

  public User(String username, int age) {
    this.username = username;
    this.age = age;
  }
}
