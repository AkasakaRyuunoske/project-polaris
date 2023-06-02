package projectpolaris.Messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopics {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("UtsuP")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
