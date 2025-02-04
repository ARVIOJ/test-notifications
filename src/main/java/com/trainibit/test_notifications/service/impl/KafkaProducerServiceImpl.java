package com.trainibit.test_notifications.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainibit.test_notifications.response.UserDataResponseKafka;
import com.trainibit.test_notifications.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.emails_sent}")
    private String topicName;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void sendMessage(UserDataResponseKafka userResponseKafka) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, userResponseKafka.getUuid().toString(), objectMapper.writeValueAsString(userResponseKafka));
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[{}] with offset=[{}]", userResponseKafka, result.getRecordMetadata().offset());
                } else {
                    log.error("Unable to send message=[{}] due to: {}", userResponseKafka, ex.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("Error converting UserResponseKafka to JSON", e);
            throw new RuntimeException("Error converting UserResponseKafka to JSON", e);
        }
    }
}