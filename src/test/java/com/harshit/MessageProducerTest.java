package com.harshit;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@MicronautTest
class MessageProducerTest {

    @Mock
    Producer messageProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        String message = "Test Message";
        messageProducer.send(message);
        verify(messageProducer, times(1)).send(message);
    }
}
