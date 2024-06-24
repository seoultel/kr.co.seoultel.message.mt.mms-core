package kr.co.seoultel.message.mt.mms.core.messages.direct.lgt;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Slf4j
@ToString
public class LgtSoapMessage extends SoapMessage {

    @Override
    public SOAPMessage toSOAPMessage() throws Exception {
        return null;
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws Exception {

    }
}

