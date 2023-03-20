package practice.others.cache.domain.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private Long id;

    private String name;

    private double price;
}
