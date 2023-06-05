package projectpolaris.Messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {
    @Bean
    public NewTopic UtsuP() {
        return TopicBuilder.name("UtsuP")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic security() {
        return TopicBuilder.name("security")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic security1() {
        return TopicBuilder.name("security")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
