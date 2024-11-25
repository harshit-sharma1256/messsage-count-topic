package com.harshit;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MessageService {

    private int writtenMessageCount = 0;

    @Inject
     Producer messageProducer;

    @Inject
    MessageConsumer messageConsumer;

    @Scheduled(fixedDelay = "1s")
    public void sendMessage() {
        String message = "Message #" + (writtenMessageCount + 1);
        messageProducer.send(message);
        writtenMessageCount++;
        System.out.println("Sent message: " + message);
    }

    @Scheduled(fixedDelay = "1s")
    public void checkMessageCount() {
        int receivedMessageCount = messageConsumer.getReceivedMessageCount(); // Call only once
        if (writtenMessageCount == receivedMessageCount) {
            System.out.println("Message count verified: " + writtenMessageCount);
        } else {
            System.out.println("Mismatch! Written: " + writtenMessageCount + ", Received: " + receivedMessageCount);
        }
    }


    public int getWrittenMessageCount() {
        return writtenMessageCount;
    }

    public void setWrittenMessageCount(int i) {
        this.writtenMessageCount = i;
    }
}

