package com.harshit;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MicronautTest
class MessageConsumerTest {

    @Mock
    MessageConsumer messageConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMessageReceived() {
        String testMessage = "Test Message";
        messageConsumer.receive(testMessage);
        verify(messageConsumer, times(1)).receive(testMessage);
    }
}
