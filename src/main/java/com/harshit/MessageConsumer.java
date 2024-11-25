package com.harshit;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Singleton;

@KafkaListener(groupId = "test-group")
@Singleton
public class MessageConsumer {

    private int receivedMessageCount = 0;

    @Topic("messageCountTopic")
    public void receive(String message) {
        receivedMessageCount++;
        System.out.println("Received message: " + message);
    }

    public int getReceivedMessageCount() {
        return receivedMessageCount;
    }
}


