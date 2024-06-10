package message.smtnt.packet.no_enc.bind;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import message.smtnt.packet.no_enc.SmtntDefaultPacketTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet.SmtntPacket;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindMessage;

import static kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol.SMTNT_BIND_METHOD_HEADER;


/**
 * {@link SmtntPacket} SmtntBindPacketTest 클래스는 SmtntBindPacket 에 대한 테스트를 제공합니다.
 */
public class SmtntBindPacketTest extends SmtntDefaultPacketTest {

    protected static final String BIND_ID = "bindId";
    protected static final String BIND_PWD = "bindPwd";
    protected static final String VERSION = "1";

    protected static final SmtntBindMessage smtntBindMessage = new SmtntBindMessage(BIND_ID, BIND_PWD);
    protected static final String jsonOfSmtntBindMessage = "{\"BindId\":\"bindId\",\"BindPwd\":\"bindPwd\",\"Version\":1}";




    /**
     * SmtntPacket의 생성자 {@link SmtntBindMessage}에 대한 테스트 케이스입니다.
     * SmtntPacket의 생성이 올바른지 확인합니다.
     *
     * @throws CryptoException 암호화 작업 중 오류가 발생한 경우
     */
    @Test
    @Override
    protected void testConstructorOfPacket() throws CryptoException {
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);

        assertNotNull(smtntBindPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");
        assertNotNull(smtntBindPacket.getBody(), "SmtntPacket의 바디는 null이 아니어야 합니다.");

        assertEquals(SMTNT_BIND_METHOD_HEADER, smtntBindPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");

        String body = (String) smtntBindPacket.getBody();
        String jsonFromBody = smtntEncryptor.decryptTextByBase64AndAes128(body).get();

        SmtntBindMessage smtntBindMessageFromJson = ConvertorUtil.convertJsonToObject(jsonFromBody, SmtntBindMessage.class);
        assertEquals(smtntBindMessage, smtntBindMessageFromJson);
    }

    /**
     * {@link SmtntPacket}의 헤더 및 바디에 대한 유효성을 검증하는 유닛 테스트입니다.
     */
    @Test
    @Override
    protected void testPacketHeaderVaule() throws CryptoException {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);

        // 테스트 중에 생성된 SmtntPacket의 헤더가 null이 아닌지 확인합니다.
        assertNotNull(smtntBindPacket.getHeader(), "SmtntPacket의 헤더는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 헤더의 메서드가 올바른지 확인합니다.
        assertEquals(SMTNT_BIND_METHOD_HEADER, smtntBindPacket.getHeader().getMethod(), "메서드는 일치해야 합니다.");
    }

    /**
     * SmtntPacket의 바디가 유효한지 검증하는 유닛 테스트입니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketBodyVaule() throws CryptoException {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);

        // 생성된 SmtntPacket의 바디가 null이 아닌지 확인합니다.
        String encryptedSmtntBindPacketBody = (String) smtntBindPacket.getBody();
        assertNotNull(encryptedSmtntBindPacketBody, "SmtntPacket의 바디는 null이 아니어야 합니다.");

        // 생성된 SmtntPacket의 바디를 복호화하여 기대값과 일치하는지 확인합니다.
        String decryptedSmtntBindPacketBody = smtntEncryptor.decryptTextByBase64AndAes128(encryptedSmtntBindPacketBody).get();
        assertEquals(decryptedSmtntBindPacketBody, jsonOfSmtntBindMessage, "SmtntPacket의 바디는 일치해야 합니다.");
    }

    /**
     * SmtntPacket을 JSON 형식으로 변환하는 유닛 테스트입니다.
     * @throws CryptoException 암호화 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToJson() throws CryptoException {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);

        // 생성된 SmtntPacket을 JSON 형식으로 변환합니다.
        String smtntBindPacketJson = smtntBindPacket.toJson();

        // 변환된 JSON이 null이 아닌지 확인합니다.
        assertNotNull(smtntBindPacketJson);
    }

    /**
     * JSON 형식의 데이터를 SmtntPacket으로 변환하는 유닛 테스트입니다.
     * @throws Exception 변환 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertFromJson() throws Exception {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);
        assertNotNull(smtntBindPacket);

        // SmtntPacket을 JSON 형식으로 변환합니다.
        String jsonOfSmtntBindPacket = smtntBindPacket.toJson();

        // JSON 형식의 데이터를 SmtntPacket으로 변환합니다.
        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(jsonOfSmtntBindPacket);

        // 변환된 SmtntPacket의 바디와 기대값이 일치하는지 확인합니다.
        SmtntBindMessage smtntBindMessage1 = (SmtntBindMessage) smtntPacket.getBody();
        assertNotNull(smtntPacket);
        assertEquals(smtntBindMessage, smtntBindMessage1);
    }

    /**
     * SmtntPacket을 ByteBuf로 변환하는 유닛 테스트입니다.
     * @throws Exception 변환 과정 중 오류가 발생할 경우
     */
    @Test
    @Override
    protected void testPacketConvertToByteBuf() throws Exception {
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);
        assertNotNull(smtntBindPacket);

        // SmtntPacket을 ByteBuf로 변환합니다.
        ByteBuf byteBuf = Unpooled.buffer();
        smtntBindPacket.toByteBuf(byteBuf);

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
        // SmtntBindMessage 객체를 사용하여 SmtntPacket을 생성합니다.
        SmtntPacket smtntBindPacket = new SmtntPacket(smtntBindMessage);
        assertNotNull(smtntBindPacket);

        // SmtntPacket을 ByteBuf로 변환합니다.
        ByteBuf byteBuf = Unpooled.buffer();
        smtntBindPacket.toByteBuf(byteBuf);

        // 변환된 ByteBuf를 다시 SmtntPacket으로 변환합니다.
        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromByteBuf(byteBuf);

        // 변환된 SmtntPacket의 헤더 및 바디가 유효한지 확인합니다.
        assertNotNull(smtntPacket);
        assertNotNull(smtntPacket.getHeader());

        SmtntBindMessage smtntBindMessage1 = (SmtntBindMessage) smtntPacket.getBody();
        assertEquals(smtntBindMessage, smtntBindMessage1);
    }
}
