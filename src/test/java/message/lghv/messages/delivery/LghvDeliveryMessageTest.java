package message.lghv.messages.delivery;


import java.nio.charset.Charset;
import java.util.List;

import kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery.LghvDeliveryMultipartData;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;
import static kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery.LghvDeliveryMultipartData.createTextMultipartData;
import static org.junit.jupiter.api.Assertions.*;

import static kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol.*;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery.LghvDeliveryMessage;


/**
 * {@link LghvDeliveryMessage}에 대한 테스트 클래스입니다.
 */
public class LghvDeliveryMessageTest {

    protected static final String DEFAULT_UMS_MSG_ID = "1234";
    protected static final String DEFAULT_RECEIVER = "01012345678";
    protected static final String DEFAULT_CALLBACK = "01012345678";
    protected static final String DEFAULT_ORIGIN_CODE = "123456789";
    protected static final String DEFAULT_SUBJECT = "제목";
    protected static final String DEFAULT_MESSAGE = "안녕하세요. 테스트 메시지입니다.";
    protected static final int DEFAULT_MESSAGE_LEN = "안녕하세요. 테스트 메시지입니다.".getBytes(Charset.forName(EUC_KR)).length;


    /**
     * {@link LghvDeliveryMessage}의 기본 생성자를 테스트합니다.
     * 메시지가 올바르게 생성되었는지 확인합니다.
     */
    @Test
    public void testConstructorByLms() {
        boolean hasText = true;
        List<String> images = List.of();

        LghvDeliveryMessage lghvDeliveryMessage = LghvDeliveryMessage.createDeliveryMessage(DELIVERY_NO_ENCODE_TYPE, DEFAULT_UMS_MSG_ID, DEFAULT_RECEIVER, DEFAULT_CALLBACK, DEFAULT_ORIGIN_CODE, DEFAULT_SUBJECT);
        lghvDeliveryMessage.setMendatoryValues(hasText, images);

        assertNotNull(lghvDeliveryMessage);
        assertEquals(lghvDeliveryMessage.getMsgType(), DELIVERY_MSG_TYPE);
        assertEquals(lghvDeliveryMessage.getMsgLen(), LMS_UNCHANGE_BODY_LEN);
        assertEquals(lghvDeliveryMessage.getDeliveryType(), LMS_DELIVERY_TYPE);
        assertEquals(lghvDeliveryMessage.getEncode(), DELIVERY_NO_ENCODE_TYPE);
        assertEquals(lghvDeliveryMessage.getContentsType(), "TXT");
        assertEquals(lghvDeliveryMessage.getMsgId(), DEFAULT_UMS_MSG_ID);
        assertEquals(lghvDeliveryMessage.getGubun(), DOMESTIC_GUBUN);
        assertEquals(lghvDeliveryMessage.getDestAddr(), DEFAULT_RECEIVER);
        assertEquals(lghvDeliveryMessage.getCallback(), DEFAULT_CALLBACK);
        assertEquals(lghvDeliveryMessage.getSubject(), DEFAULT_SUBJECT);
        assertEquals(lghvDeliveryMessage.getReseller(), DEFAULT_ORIGIN_CODE);

        lghvDeliveryMessage.setText(DEFAULT_MESSAGE);
        assertEquals(lghvDeliveryMessage.getMsgLen(), LMS_UNCHANGE_BODY_LEN + DEFAULT_MESSAGE_LEN);
    }

    @Test
    public void testConstructorByMms() {
        boolean hasText = true;
        String fileName = "0G9THKKE6SACF";
        byte[] imageByteArr = new byte[100];
        List<String> images = List.of(fileName);

        LghvDeliveryMessage lghvDeliveryMessage = LghvDeliveryMessage.createDeliveryMessage(DELIVERY_NO_ENCODE_TYPE, DEFAULT_UMS_MSG_ID, DEFAULT_RECEIVER, DEFAULT_CALLBACK, DEFAULT_ORIGIN_CODE, DEFAULT_SUBJECT);
        lghvDeliveryMessage.setMendatoryValues(hasText, images);


        LghvDeliveryMultipartData textData = createTextMultipartData(DEFAULT_MESSAGE);
        lghvDeliveryMessage.addMediapartData(textData);

        LghvDeliveryMultipartData mediaData = LghvDeliveryMultipartData.createImageMultipartData(fileName, fileName, imageByteArr);
        lghvDeliveryMessage.addMediapartData(mediaData);

        assertNotNull(lghvDeliveryMessage);
        assertEquals(lghvDeliveryMessage.getMsgType(), DELIVERY_MSG_TYPE);
        assertEquals(lghvDeliveryMessage.getMsgLen(), MMS_UNCHANGE_BODY_LEN);
        assertEquals(lghvDeliveryMessage.getDeliveryType(), MMS_DELIVERY_TYPE);
        assertEquals(lghvDeliveryMessage.getMsgLen(), MMS_UNCHANGE_BODY_LEN);
        assertEquals(lghvDeliveryMessage.getEncode(), DELIVERY_NO_ENCODE_TYPE);
        assertEquals(lghvDeliveryMessage.getContentsType(), "IMT");
        assertEquals(lghvDeliveryMessage.getMsgId(), DEFAULT_UMS_MSG_ID);
        assertEquals(lghvDeliveryMessage.getGubun(), DOMESTIC_GUBUN);
        assertEquals(lghvDeliveryMessage.getDestAddr(), DEFAULT_RECEIVER);
        assertEquals(lghvDeliveryMessage.getCallback(), DEFAULT_CALLBACK);
        assertEquals(lghvDeliveryMessage.getSubject(), DEFAULT_SUBJECT);
        assertEquals(lghvDeliveryMessage.getReseller(), DEFAULT_ORIGIN_CODE);
    }

    @Test
    public void testEqualsAndHashCode() {
        LghvDeliveryMessage message1 = getDefaultLmsMessage();
        LghvDeliveryMessage message2 = getDefaultLmsMessage();
        LghvDeliveryMessage message3 = new LghvDeliveryMessage();

        assertEquals(message1, message2);
        assertNotEquals(message1, message3);
        assertEquals(message1.hashCode(), message2.hashCode());
        assertNotEquals(message1.hashCode(), message3.hashCode());
    }

    public static LghvDeliveryMessage getDefaultLmsMessage() {
        boolean hasText = true;
        List<String> images = List.of();

        LghvDeliveryMessage lghvDeliveryMessage = LghvDeliveryMessage.createDeliveryMessage(DELIVERY_NO_ENCODE_TYPE, DEFAULT_UMS_MSG_ID, DEFAULT_RECEIVER, DEFAULT_CALLBACK, DEFAULT_ORIGIN_CODE, DEFAULT_SUBJECT);
        lghvDeliveryMessage.setMendatoryValues(hasText, images);

        return lghvDeliveryMessage;
    }
}
