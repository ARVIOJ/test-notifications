package com.trainibit.test_notifications.service;

import com.trainibit.test_notifications.response.UserDataResponseKafka;

public interface KafkaProducerService {
    void sendMessage(UserDataResponseKafka userResponseKafka);
}
