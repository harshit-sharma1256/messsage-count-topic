package com.harshit;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
class MessageServiceTest {

    @Mock
    Producer messageProducer;

    @Mock
    MessageConsumer messageConsumer;

    @InjectMocks
    MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        // Arrange
        int initialWrittenMessageCount = messageService.getWrittenMessageCount();
        String expectedMessage = "Message #" + (initialWrittenMessageCount + 1);

        messageService.sendMessage();
        verify(messageProducer, times(1)).send(expectedMessage);
    }

    @Test
    void testCheckMessageCount_MatchingCounts() {

        messageService.setWrittenMessageCount(5); // Simulate 5 messages written
        when(messageConsumer.getReceivedMessageCount()).thenReturn(5);

        messageService.checkMessageCount();
        verify(messageConsumer, times(1)).getReceivedMessageCount();
    }

    @Test
    void testCheckMessageCount_MismatchedCounts() {

        messageService.setWrittenMessageCount(5); // 5 messages written
        when(messageConsumer.getReceivedMessageCount()).thenReturn(3); // Only 3 messages received

        messageService.checkMessageCount();
        verify(messageConsumer, times(1)).getReceivedMessageCount();

    }
}
