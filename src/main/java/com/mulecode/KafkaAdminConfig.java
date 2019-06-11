package com.mulecode;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaAdminConfig {

    @Autowired
    Environment environment;

    /**
     * If you define a KafkaAdmin bean in your application context, it can automatically add topics to the broker.
     * Simply add a NewTopic @Bean for each topic to the application context.
     * @return
     */
    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic registrationPersonTopic() {

        return new NewTopic("registration_person", 10, (short) 1);
    }

    @Bean
    public NewTopic registrationPersonUpperTopic() {

        return new NewTopic("registration_person_upper", 10, (short) 1);
    }

}
