package de.ashish.vehicles;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    public static final String POSITIONS_TOPIC = "vehicle-positions";

    @Bean
    NewTopic positionsTopic() {
        return TopicBuilder.name(POSITIONS_TOPIC).partitions(3).build();
    }
}
