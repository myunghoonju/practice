package practice.others;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class JsonNodeTest {

  private ObjectMapper mapper;

  @BeforeEach
  void init() {
    mapper = new ObjectMapper();
  }

  @Test
  void convert_object_to_json_test() throws JsonProcessingException {
    ObjectClass o = new ObjectClass();
    o.setName("name");
    o.setAge(1);
    o.setAddress("address");

    JsonNode json = mapper.convertValue(o, JsonNode.class);
    String name = json.path("name").asText();
    String age = json.path("age").asText();
    String address = json.path("address").asText();

    log.info("name = {}, age = {}, address = {}", name, age, address);

    ObjectNode node = mapper.createObjectNode();
    node.put("name", "newName");
    node.put("age", 2);

    ObjectClass newNode = mapper.treeToValue(node, ObjectClass.class);

    log.info("name = {}, age = {}", newNode.getName(), newNode.getAge());
  }

  @Getter @Setter
  @NoArgsConstructor
  static class ObjectClass {
    private String name;
    private int age;
    private String address;
  }
}
