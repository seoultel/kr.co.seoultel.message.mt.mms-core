package kr.co.seoultel.message.mt.mms.core.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageHistoryTest {

    @Test
    void testConstructorWithmessageId() {
        String messageId = "12345";
        MessageHistory messageHistory = new MessageHistory(messageId);
        assertNotNull(messageHistory);
        assertEquals(messageId, messageHistory.getMessageId());
        assertNotNull(messageHistory.getSubmitTime());
    }

    @Test
    void testIsExpire() {
        String messageId = "12345";
        MessageHistory messageHistory = new MessageHistory(messageId);
        assertFalse(messageHistory.isExpire());
    }


    @Test
    void testEqualsAndHashCode() {
        String messageId1 = "12345";
        String messageId2 = "54321";
        MessageHistory messageHistory1 = new MessageHistory(messageId1);
        MessageHistory messageHistory2 = new MessageHistory(messageId1);
        MessageHistory messageHistory3 = new MessageHistory(messageId2);

        // Check equals
        assertTrue(messageHistory1.equals(messageHistory2));
        assertFalse(messageHistory1.equals(messageHistory3));

        // Check hashCode
        assertEquals(messageHistory1.hashCode(), messageHistory2.hashCode());
        assertNotEquals(messageHistory1.hashCode(), messageHistory3.hashCode());
    }
}
