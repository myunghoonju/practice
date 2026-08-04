package practice.others.mongo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private MongoTemplate mongoTemplate;

  @BeforeEach
  void cleanUp() {
    mongoTemplate.remove(new Query(), Product.class);
  }

  @Test
  void upsertCreatesThenUpdatesSameDocument() {
    Product created = productService.upsert(new Product("Widget A", "tools", new BigDecimal("9.99"), 100));
    assertThat(created.getId()).isNotNull();

    Product updated = productService.upsert(new Product("Widget A", "tools", new BigDecimal("12.99"), 80));

    assertThat(updated.getId()).isEqualTo(created.getId());
    assertThat(updated.getPrice()).isEqualByComparingTo("12.99");
    assertThat(updated.getStock()).isEqualTo(80);
    assertThat(mongoTemplate.count(new Query(), Product.class)).isEqualTo(1);
  }

  @Test
  void upsertAllCreatesAndUpdatesInBulk() {
    productService.upsert(new Product("Widget A", "tools", new BigDecimal("9.99"), 100));

    ProductService.BulkUpsertSummary summary = productService.upsertAll(List.of(
        new Product("Widget A", "tools", new BigDecimal("11.99"), 90),
        new Product("Widget B", "tools", new BigDecimal("14.99"), 50),
        new Product("Widget C", "parts", new BigDecimal("4.50"), 200)));

    assertThat(summary.matchedCount()).isEqualTo(1);
    assertThat(summary.upsertedCount()).isEqualTo(2);
    assertThat(mongoTemplate.count(new Query(), Product.class)).isEqualTo(3);
  }

  @Test
  void concurrentUpsertsOnNewNameDoNotThrowAndConverge() throws InterruptedException {
    int threadCount = 8;
    ExecutorService pool = Executors.newFixedThreadPool(threadCount);
    CountDownLatch ready = new CountDownLatch(threadCount);
    CountDownLatch start = new CountDownLatch(1);
    AtomicInteger failures = new AtomicInteger();

    for (int i = 0; i < threadCount; i++) {
      int stock = 10 + i;
      pool.submit(() -> {
        ready.countDown();
        try {
          start.await();
          productService.upsert(new Product("Contested Widget", "race", new BigDecimal("1.00"), stock));
        } catch (Exception e) {
          failures.incrementAndGet();
        }
      });
    }
    ready.await();
    start.countDown();
    pool.shutdown();
    pool.awaitTermination(10, TimeUnit.SECONDS);

    assertThat(failures.get()).isZero();
    assertThat(mongoTemplate.count(new Query(), Product.class)).isEqualTo(1);
  }
}
