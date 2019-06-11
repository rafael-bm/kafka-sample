package com.mulecode.stream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class MuleKafkaStream {

    public static Map<String, Object> streamConfig = Map.of(
            StreamsConfig.APPLICATION_ID_CONFIG, "testStreams",
            StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
            StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName(),
            StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
            StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName()
    );

    @Bean
    public StreamsConfig kStreamsConfigs() {
        return new StreamsConfig(streamConfig);
    }

    @Bean
    public KStream<String, String> toUpperCaseStream(StreamsBuilder kStreamBuilder) {

        var stream = kStreamBuilder.stream(
                "registration_person",
                Consumed.with(
                        Serdes.String(),
                        Serdes.String()
                )
        );

        stream.map((key, value) -> KeyValue.pair(key, value.toUpperCase()))
                .to(
                        "registration_person_upper",
                        Produced.with(
                                Serdes.String(),
                                Serdes.String())
                );

        return stream;
    }
}
