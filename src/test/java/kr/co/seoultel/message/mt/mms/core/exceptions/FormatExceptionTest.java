package kr.co.seoultel.message.mt.mms.core.exceptions;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.UmsMsgIdFormatException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static kr.co.seoultel.message.core.dto.MessageDelivery.TYPE_SUBMIT_ACK;

public class FormatExceptionTest {

    @Test
    public void umsMsgIdFormatExceptionTest() {
        MessageDelivery messageDelivery = new MessageDelivery();
        UmsMsgIdFormatException exception = new UmsMsgIdFormatException(messageDelivery, TYPE_SUBMIT_ACK);

        System.out.println(exception);
    }
}
