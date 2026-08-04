package practice.others.mongo;

import com.google.common.collect.Lists;
import com.mongodb.ErrorCategory;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.BulkOperationException;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Slf4j
@Service
public class ProductService {

  private static final int BULK_UPSERT_BATCH_SIZE = 500;

  private final MongoTemplate mongoTemplate;

  public ProductService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public List<Product> findByCategoryAbovePrice(String category, BigDecimal minPrice, Pageable pageable) {
    Query query = new Query(Criteria.where("category").is(category).and("price").gte(minPrice))
        .with(pageable);
    return mongoTemplate.find(query, Product.class);
  }

  public void restock(String productId, int delta) {
    Query query = new Query(Criteria.where("id").is(productId));
    Update update = new Update().inc("stock", delta);
    UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);
    if (result.getMatchedCount() == 0) {
      throw new ProductNotFoundException(productId);
    }
  }

  public Product upsert(Product product) {
    Query query = new Query(Criteria.where("name").is(product.getName()));
    Update update = buildUpsertUpdate(product);
    FindAndModifyOptions options = FindAndModifyOptions.options().upsert(true).returnNew(true);
    try {
      return mongoTemplate.findAndModify(query, update, options, Product.class);
    } catch (DuplicateKeyException e) {
      log.warn("Duplicate key race upserting product '{}', retrying once", product.getName());
      return mongoTemplate.findAndModify(query, update, options, Product.class);
    }
  }

  public BulkUpsertSummary upsertAll(List<Product> products) {
    long matched = 0;
    long modified = 0;
    long upserted = 0;
    for (List<Product> batch : Lists.partition(products, BULK_UPSERT_BATCH_SIZE)) {
      for (BulkWriteResult result : upsertBatchWithRetry(batch)) {
        matched += result.getMatchedCount();
        modified += result.getModifiedCount();
        upserted += result.getUpserts().size();
      }
    }
    return new BulkUpsertSummary(matched, modified, upserted);
  }

  // Concurrent upserts on the same not-yet-existing name can race between the "not found"
  // check and the insert; the losing operation fails with a duplicate key error instead of
  // matching the winner's document. Retrying that operation alone lets it fall through to
  // the update path now that the document exists.
  private List<BulkWriteResult> upsertBatchWithRetry(List<Product> batch) {
    try {
      return List.of(executeUpsertBatch(batch));
    } catch (BulkOperationException e) {
      List<Product> retryCandidates = e.getErrors().stream()
          .filter(error -> error.getCategory() == ErrorCategory.DUPLICATE_KEY)
          .map(error -> batch.get(error.getIndex()))
          .toList();
      if (retryCandidates.size() != e.getErrors().size()) {
        throw e;
      }
      log.warn("Duplicate key race on {} upsert(s), retrying once", retryCandidates.size());
      return List.of(e.getResult(), executeUpsertBatch(retryCandidates));
    }
  }

  private BulkWriteResult executeUpsertBatch(List<Product> batch) {
    BulkOperations bulkOps = mongoTemplate.bulkOps(BulkMode.UNORDERED, Product.class);
    for (Product product : batch) {
      Query query = new Query(Criteria.where("name").is(product.getName()));
      bulkOps.upsert(query, buildUpsertUpdate(product));
    }
    return bulkOps.execute();
  }

  private static Update buildUpsertUpdate(Product product) {
    return new Update()
        .set("category", product.getCategory())
        .set("price", product.getPrice())
        .set("stock", product.getStock());
  }

  public record BulkUpsertSummary(long matchedCount, long modifiedCount, long upsertedCount) {
  }

  public Product decrementStockIfAvailable(String productId, int quantity) {
    Query query = new Query(Criteria.where("id").is(productId).and("stock").gte(quantity));
    Update update = new Update().inc("stock", -quantity);
    Product updated = mongoTemplate.findAndModify(query, update,
        FindAndModifyOptions.options().returnNew(true), Product.class);
    if (updated == null) {
      throw new InsufficientStockException(productId, quantity);
    }
    return updated;
  }

  public List<CategoryCount> countByCategory() {
    Aggregation aggregation = newAggregation(
        match(Criteria.where("stock").gt(0)),
        group("category").count().as("count"),
        project("count").and("_id").as("category"));
    AggregationResults<CategoryCount> results =
        mongoTemplate.aggregate(aggregation, "products", CategoryCount.class);
    return results.getMappedResults();
  }

  public record CategoryCount(String category, long count) {
  }
}
