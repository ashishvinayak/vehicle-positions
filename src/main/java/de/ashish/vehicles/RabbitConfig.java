package de.ashish.vehicles;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "vehicle-events";
    public static final String ARRIVALS_QUEUE = "arrivals";

    @Bean
    TopicExchange vehicleEventsExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    Queue arrivalsQueue() {
        return new Queue(ARRIVALS_QUEUE);
    }

    @Bean
    Binding arrivalsBinding(Queue arrivalsQueue, TopicExchange vehicleEventsExchange) {
        return BindingBuilder.bind(arrivalsQueue).to(vehicleEventsExchange).with("arrival.*");
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
