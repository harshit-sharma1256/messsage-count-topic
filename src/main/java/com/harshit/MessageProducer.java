package com.harshit;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Singleton;

@KafkaClient
@Singleton
interface Producer{
    @Topic("messageCountTopic")
    void send(String message);
}



