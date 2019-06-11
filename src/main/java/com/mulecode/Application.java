package com.mulecode;

import com.github.javafaker.Faker;
import com.mulecode.model.MessageWrapper;
import com.mulecode.producer.MuleKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EnableKafka
@EnableKafkaStreams
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    MuleKafkaProducer muleKafkaProducer;

    public static void main(String[] args) {

        LOG.debug("Application starting ...");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LOG.debug("Command line starting");

        Runnable myRun = () -> {

            var fake = new Faker();

            var message = new MessageWrapper();
            message.setId(UUID.randomUUID().toString());
            message.setPublisher(fake.lordOfTheRings().location());

            muleKafkaProducer.send(
                    "registration_person",
                    message
            );

        };

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(myRun, 1, 1, TimeUnit.SECONDS);
    }
}
