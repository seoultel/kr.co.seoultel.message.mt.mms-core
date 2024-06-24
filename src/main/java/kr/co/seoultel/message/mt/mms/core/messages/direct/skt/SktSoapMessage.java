package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class SktSoapMessage extends SoapMessage {

    @Override
    public SOAPMessage toSOAPMessage() throws Exception {
        return null;
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {

    }
}

