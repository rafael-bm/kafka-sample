package com.mulecode.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MulecodeKafkaListener {

    private static Logger LOG = LoggerFactory.getLogger(MulecodeKafkaListener.class);

    @KafkaListener(topics = "registration_person_upper", groupId = "myGroup")
    public void consume(String message) {

        LOG.debug("Message consumed: {}", message);
    }

}
