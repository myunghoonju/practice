package practice.others.cache.amqp;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType.SIMPLE;

@Slf4j
@Configuration
public class ConsumerConfig {

    @Bean
    public ConnectionFactory firstConnection() {
        CachingConnectionFactory conn = new CachingConnectionFactory();
        conn.setHost("localhost");
        conn.setPort(5672);
        conn.setUsername("guest");
        conn.setPassword("guest");
        conn.setPublisherConfirmType(SIMPLE);
        return conn;
    }

    @Bean
    public RabbitTemplate firstRabbit() {
        RabbitTemplate firstRabbit = new RabbitTemplate(firstConnection());
        firstRabbit.setMessageConverter(converter());

        return firstRabbit;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
