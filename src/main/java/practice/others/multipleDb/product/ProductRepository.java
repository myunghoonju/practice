package practice.others.multipleDb.product;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.others.multipleDb.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }
