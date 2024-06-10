package kr.co.seoultel.message.mt.mms.core.exceptions;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.UmsMsgIdFormatException;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;
import org.junit.jupiter.api.Test;


public class FormatExceptionTest {

    @Test
    public void umsMsgIdFormatExceptionTest() {
        MessageDelivery messageDelivery = new MessageDelivery();
        UmsMsgIdFormatException exception = new UmsMsgIdFormatException(messageDelivery, DeliveryType.SUBMIT_ACK);

        System.out.println(exception);
    }
}
