package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.bind;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindMessage;
import org.junit.jupiter.api.Test;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.*;
import static org.junit.jupiter.api.Assertions.*;

import kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindAckMessage;


/**
 * {@link SmtntPacket} SmtntBindAckPacketTest 클래스는 SmtntBindAckPacket 에 대한 테스트를 제공합니다.
 */
public class SmtntBindAckPacketTest extends SmtntDefaultPacketTest {

    protected static final String BIND_ID = "bindId";

    protected static final SmtntBindAckMessage smtntBindAckMessage = SmtntBindAckMessage.builder()
                                                                                        .result(BIND_SUCCESS)
                                                                                        .bindId(BIND_ID)
                                                                                        .speed(100)
                                                                                        .beginTime(1)
                                                                                        .effectiveTime(2)
                                                                                        .endTime(3)
                                                                                        .build();

    protected static final String jsonOfSmtntBindAckMessage = "{\"Result\":0,\"BindId\":\"bindId\",\"Speed\":100,\"BeginTime\":1,\"EndTime\":3,\"EffectiveTime\":2}";


    /**
     * SmtntPacket의 생성자 {@link SmtntBindAckMessage}에 대한 테스트 케이스입니다.
     * SmtntPacket의 생성이 올바른지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        // SmtntBindAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);

        // 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntBindAckPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_BIND_ACK_METHOD_HEADER, smtntBindAckPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        // 생성된 SmtntPacket의 바디가 null이 아닌지 확인합니다.
        assertNotNull(smtntBindAckPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        String encryptedBody = (String) smtntBindAckPacket.getBody();
        String decryptedBody = smtntEncryptor.decryptTextByBase64AndAes128(encryptedBody).get();

        assertEquals(decryptedBody, jsonOfSmtntBindAckMessage);
    }

    /**
     * {@link SmtntPacket}의 헤더 및 바디에 대한 유효성을 검증하는 유닛 테스트입니다.
     */
    @Test
    @Override
    protected void testPacketHeaderVaule() throws CryptoException {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindAckMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntBindPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_BIND_ACK_METHOD_HEADER, smtntBindPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }

    /**
     * SmtntPacket의 바디가 유효한지 검증하는 유닛 테스트입니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntBindAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);
        assertNotNull(smtntBindAckPacket);

        // 생성된 SmtntPacket의 바디가 null이 아닌지 확인합니다.
        String encryptedSmtntBindAckPacketBody = (String) smtntBindAckPacket.getBody();
        assertNotNull(encryptedSmtntBindAckPacketBody, "SmtntPacket의 바디는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 바디를 복호화하여 기대값과 일치하는지 확인합니다.
        String decryptedSmtntBindAckPacketBody = smtntEncryptor.decryptTextByBase64AndAes128(encryptedSmtntBindAckPacketBody).get();
        assertEquals(decryptedSmtntBindAckPacketBody, jsonOfSmtntBindAckMessage, "SmtntPacket의 바디는 일치해야 합니다.");
    }


    /**
     * SmtntPacket을 JSON 형식으로 변환하는 유닛 테스트입니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToJson() throws CryptoException {
        // SmtntBindAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);

        // 생성된 SmtntPacket을 JSON 형식으로 변환합니다.
        String smtntBindAckPacketJson = smtntBindAckPacket.toJson();

        // 변환된 JSON이 null이 아닌지 확인합니다.
        assertNotNull(smtntBindAckPacketJson);
    }

    /**
     * JSON 형식의 데이터를 SmtntPacket으로 변환하는 유닛 테스트입니다.
     * @throws Exception 변환 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromJson() throws Exception {
        // SmtntBindAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);
        assertNotNull(smtntBindAckPacket);

        // SmtntPacket을 JSON 형식으로 변환합니다.
        String jsonOfSmtntBindAckPacket = smtntBindAckPacket.toJson();

        // JSON 형식의 데이터를 SmtntPacket으로 변환합니다.
        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfSmtntBindAckPacket);

        // 변환된 SmtntPacket의 바디와 기대값이 일치하는지 확인합니다.
        SmtntBindAckMessage smtntBindAckMessage1 = (SmtntBindAckMessage) smtntPacket.getBody();
        assertNotNull(smtntPacket);
        assertEquals(smtntBindAckMessage, smtntBindAckMessage1);
    }

    /**
     * SmtntPacket을 ByteBuf로 변환하는 유닛 테스트입니다.
     * @throws Exception 변환 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToByteBuf() throws Exception {
        // SmtntBindAckMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);
        assertNotNull(smtntBindAckPacket);

        // SmtntPacket을 ByteBuf로 변환합니다.
        ByteBuf byteBuf = Unpooled.buffer();
        smtntBindAckPacket.toByteBuf(byteBuf);

        // 변환된 ByteBuf의 길이가 1 이상인지 확인합니다.
        assertTrue(byteBuf.writerIndex() >= 1);
    }

    /**
     * ByteBuf를 SmtntPacket으로 변환하는 유닛 테스트입니다.
     * @throws Exception 변환 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromByteBuf() throws Exception {
        SmtntPacket smtntBindAckPacket = new SmtntPacket(smtntBindAckMessage);
        assertNotNull(smtntBindAckPacket);

        // SmtntPacket을 ByteBuf로 변환합니다.
        ByteBuf byteBuf = Unpooled.buffer();
        smtntBindAckPacket.toByteBuf(byteBuf);

        // 변환된 ByteBuf를 다시 SmtntPacket으로 변환합니다.
        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        // 변환된 SmtntPacket의 헤더 및 바디가 유효한지 확인합니다.
        assertNotNull(smtntPacket);
        assertNotNull(smtntPacket.getHeader());

        SmtntBindAckMessage smtntBindAckMessage1 = (SmtntBindAckMessage) smtntPacket.getBody();
        assertEquals(smtntBindAckMessage, smtntBindAckMessage1);
    }

}
