package com.amsidh.mvc.kafkaconsumerproject;

import com.amsidh.mvc.kafkaconsumerproject.model.Share;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class KafkaConsumerProjectApplication {

    private final ObjectMapper objectMapper;

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerProjectApplication.class, args);
    }

    @KafkaListener(topics = {"test"}, groupId = "myGroupId")
    public void consumeMessage(String share) throws JsonProcessingException {
        log.info(share);
        Share parsedShare = objectMapper.readValue(share, Share.class);
        log.info(parsedShare.toString());
    }
}
