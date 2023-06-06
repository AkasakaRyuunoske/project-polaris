package projectpolaris.Messaging;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Log4j2
public class KafkaTopics {

    // Default Configurations
    @Value("${polaris.kafka.default-partitions-count}")
    private int default_partitions_count;

    @Value("${polaris.kafka.default-replicas-count}")
    private int default_replicas_count;


    //Topic Names

    @Value("${polaris.kafka.topics.utsup}")
    private String utsup;

    @Value("${polaris.kafka.topics.statistics}")
    private String statistics;

    @Value("${polaris.kafka.topics.security}")
    private String security;

    @Value("${polaris.kafka.topics.data-layer}")
    private String dataLayer;

    @Value("${polaris.kafka.topics.front-end-gateway}")
    private String frontEndGateway;

    @Value("${polaris.kafka.topics.errors-security}")
    private String errorsSecurity;

    @Value("${polaris.kafka.topics.errors-REST}")
    private String errorsREST;

    @Value("${polaris.kafka.topics.errors-SOAP}")
    private String errorsSOAP;

    @Value("${polaris.kafka.topics.errors-internal}")
    private String errorsInternal;

    @Bean
    public NewTopic utsuP() {
        log.info("[Topic created]: " + utsup);
        return TopicBuilder.name(utsup)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic security() {
        log.info("[Topic created]:" + security);
        return TopicBuilder.name(security)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic statistics() {
        log.info("[Topic created]: " + statistics);
        return TopicBuilder.name(statistics) // #todo maybe more topics, like statistics-front-end, statistics-SBES, statistics-MBE, statistics-SBEP, statistics-errors
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic dataLayer() {
        log.info("[Topic created]: " + dataLayer);
        return TopicBuilder.name(dataLayer)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic frontEndGateway() {
        log.info("[Topic created]: " + frontEndGateway);
        return TopicBuilder.name(frontEndGateway)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    // Error dedicated Topics

    @Bean
    public NewTopic errorsSecurity() {
        log.info("[Topic created]: " + errorsSecurity);
        return TopicBuilder.name(errorsSecurity)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic errorsREST() {
        log.info("[Topic created]: " + errorsREST);
        return TopicBuilder.name(errorsREST)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic errorsSOAP() {
        log.info("[Topic created]: " + errorsSOAP);
        return TopicBuilder.name(errorsSOAP)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }

    @Bean
    public NewTopic errorsInternal() {
        log.info("[Topic created]: " + errorsInternal);
        return TopicBuilder.name(errorsInternal)
                .partitions(default_partitions_count)
                .replicas(default_replicas_count)
                .build();
    }
}
