package kr.co.seoultel.message.mt.mms.core.common.interfaces;

import io.netty.buffer.ByteBuf;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;

public interface ConvertableToSOAPMessage {

    SOAPMessage toSOAPMessage() throws Exception;
    void fromSOAPMessage(SOAPMessage soapMessage) throws Exception;
}
