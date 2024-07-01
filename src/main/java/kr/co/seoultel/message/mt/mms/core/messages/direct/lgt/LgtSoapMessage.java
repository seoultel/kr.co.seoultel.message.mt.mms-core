package kr.co.seoultel.message.mt.mms.core.messages.direct.lgt;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ToString
public class LgtSoapMessage extends SoapMessage {

    public LgtSoapMessage() throws SOAPException {
        super();
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        return null;
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {

    }

    @Override
    public String convertSOAPMessageToString() throws SOAPException, IOException {
        return null;
    }
}

