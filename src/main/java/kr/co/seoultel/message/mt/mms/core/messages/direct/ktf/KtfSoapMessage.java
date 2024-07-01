package kr.co.seoultel.message.mt.mms.core.messages.direct.ktf;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public abstract class KtfSoapMessage extends SoapMessage {

    protected String localPart;


    protected final String serviceType = "MMSMT1";
    protected final String mm7Version = "1.0";


    protected final String messageClass = "GENERAL";
    protected final String deliveryReport = "TRUE";
    protected final String readReply = "TRUE";

    public KtfSoapMessage() throws SOAPException {
        super();
    }

    public KtfSoapMessage(String localPart) throws SOAPException {
        super();

        this.localPart = localPart;
    }


}
