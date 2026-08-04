package practice.others.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

  @Id
  private String id;

  @Indexed(unique = true)
  private String name;

  @Indexed
  private String category;

  private BigDecimal price;

  private int stock;

  @Version
  private Long version;

  public Product(String name, String category, BigDecimal price, int stock) {
    this.name = name;
    this.category = category;
    this.price = price;
    this.stock = stock;
  }
}
