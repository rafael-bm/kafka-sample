package com.mulecode.producer;

import com.mulecode.model.MessageWrapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;


@Component
public class MuleKafkaProducer {

    private static Logger LOG = LoggerFactory.getLogger(MuleKafkaProducer.class);

    private KafkaTemplate kafkaTemplate;

    public MuleKafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, MessageWrapper message) {

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                topic,
                UUID.randomUUID().toString(),
                message.toString()
        );

        kafkaTemplate.send(record);
//                .addCallback(callback());

        LOG.debug("Message sent - Topic: {} Message: {}", topic, message);
    }

    public ListenableFutureCallback<SendResult<Integer, String>> callback() {

        return new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                LOG.info("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("error: {}", ex.getMessage());
            }

        };
    }

}
