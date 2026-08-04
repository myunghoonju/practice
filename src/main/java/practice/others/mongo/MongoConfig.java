package practice.others.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.BigDecimalRepresentation;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.MongoConverterConfigurationAdapter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Value("${mongodb.uri:mongodb://localhost:27017}")
  private String uri;

  @Value("${mongodb.database:test}")
  private String database;

  @Value("${mongodb.secondary-database:mutex}")
  private String secondaryDatabase;

  @Override
  public String getDatabaseName() {
    return database;
  }

  // Second database on the same cluster/connection. Repositories still resolve to the
  // primary database above via @EnableMongoRepositories; services that need "mutex" data
  // inject this template directly with @Qualifier("mutexMongoTemplate").
  @Bean
  public MongoDatabaseFactory mutexMongoDatabaseFactory(MongoClient mongoClient) {
    return new SimpleMongoClientDatabaseFactory(mongoClient, secondaryDatabase);
  }

  @Bean
  public MongoTemplate mutexMongoTemplate(
      @Qualifier("mutexMongoDatabaseFactory") MongoDatabaseFactory mutexMongoDatabaseFactory,
      MappingMongoConverter mappingMongoConverter) {
    return new MongoTemplate(mutexMongoDatabaseFactory, mappingMongoConverter);
  }

  @Override
  protected void configureClientSettings(MongoClientSettings.Builder builder) {
    builder
        .applyConnectionString(new ConnectionString(uri))
        .applyToConnectionPoolSettings(pool -> pool
            .minSize(5)
            .maxSize(50)
            .maxConnectionIdleTime(60, TimeUnit.SECONDS)
            .maxWaitTime(2, TimeUnit.SECONDS))
        .applyToSocketSettings(socket -> socket
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS))
        .readPreference(ReadPreference.primaryPreferred())
        .writeConcern(WriteConcern.MAJORITY.withWTimeout(5, TimeUnit.SECONDS))
        .retryWrites(true)
        .retryReads(true);
  }

  @Override
  protected void configureConverters(MongoConverterConfigurationAdapter adapter) {
    adapter.bigDecimal(BigDecimalRepresentation.DECIMAL128);
  }

  // Off by default in Spring Data Mongo (index changes shouldn't happen implicitly at
  // startup in real production). Enabled here only because this is a practice sandbox and
  // the @Indexed annotations need to actually take effect for the tests to be meaningful.
  @Override
  protected boolean autoIndexCreation() {
    return true;
  }

  @Bean
  @Override
  public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory,
      MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
    MappingMongoConverter converter =
        super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return converter;
  }
}
