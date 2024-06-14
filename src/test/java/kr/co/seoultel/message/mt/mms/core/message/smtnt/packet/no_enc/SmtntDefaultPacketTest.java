package kr.co.seoultel.message.mt.mms.core.message.smtnt.packet.no_enc;

import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.encrypt.SmtntEncryptor;
import org.junit.jupiter.api.BeforeAll;

public abstract class SmtntDefaultPacketTest {

    protected static final String ENC_KEY = "@#$DFGH%^&sdd122";
    protected static final String IV_KEY = "@#%%DSVS#CVDG@#4";

    protected static final SmtntEncryptor smtntEncryptor = new SmtntEncryptor();

    @BeforeAll
    public static void setupSmtntEncryptor() {
        SmtntEncryptor.initialize(ENC_KEY, IV_KEY);
    }

    protected abstract void testConstructorOfPacket() throws CryptoException;


    protected abstract void testPacketHeaderVaule() throws CryptoException;
    protected abstract void testPacketBodyVaule() throws CryptoException;


    protected abstract void testPacketConvertToJson() throws CryptoException;
    protected abstract void testPacketConvertFromJson() throws Exception;

    protected abstract void testPacketConvertToByteBuf() throws Exception;
    protected abstract void testPacketConvertFromByteBuf() throws Exception;

}
