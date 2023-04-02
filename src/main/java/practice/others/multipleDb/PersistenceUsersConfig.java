package practice.others.multipleDb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@PropertySource(value = {"classpath:application.yml"})
@EnableJpaRepositories(basePackages = "practice.others.multipleDb.domain",
                       entityManagerFactoryRef = "usersEntityManager",
                       transactionManagerRef = "usersTransactionManager"
)
public class PersistenceUsersConfig {

    private final Environment env;

    public PersistenceUsersConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean usersEntityManager() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(usersDataSource());
        factoryBean.setPackagesToScan("practice.others.multipleDb.domain");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", env.getProperty("hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.mysql.database-platform"));
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    @Primary
    @Bean
    public DataSource usersDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.mysql.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.mysql.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mysql.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mysql.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager usersTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(usersEntityManager().getObject());

        return jpaTransactionManager;
    }
}
