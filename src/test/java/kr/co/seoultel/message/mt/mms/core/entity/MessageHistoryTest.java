package kr.co.seoultel.message.mt.mms.core.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageHistoryTest {

    @Test
    void testConstructorWithUmsMsgId() {
        String umsMsgId = "12345";
        MessageHistory messageHistory = new MessageHistory(umsMsgId);
        assertNotNull(messageHistory);
        assertEquals(umsMsgId, messageHistory.getUmsMsgId());
        assertNotNull(messageHistory.getSubmitTime());
    }

    @Test
    void testIsExpire() {
        String umsMsgId = "12345";
        MessageHistory messageHistory = new MessageHistory(umsMsgId);
        assertFalse(messageHistory.isExpire());
    }


    @Test
    void testEqualsAndHashCode() {
        String umsMsgId1 = "12345";
        String umsMsgId2 = "54321";
        MessageHistory messageHistory1 = new MessageHistory(umsMsgId1);
        MessageHistory messageHistory2 = new MessageHistory(umsMsgId1);
        MessageHistory messageHistory3 = new MessageHistory(umsMsgId2);

        // Check equals
        assertTrue(messageHistory1.equals(messageHistory2));
        assertFalse(messageHistory1.equals(messageHistory3));

        // Check hashCode
        assertEquals(messageHistory1.hashCode(), messageHistory2.hashCode());
        assertNotEquals(messageHistory1.hashCode(), messageHistory3.hashCode());
    }
}
