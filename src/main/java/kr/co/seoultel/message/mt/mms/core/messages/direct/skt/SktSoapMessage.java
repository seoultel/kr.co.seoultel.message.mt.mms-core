package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ToString
public abstract class SktSoapMessage extends SoapMessage {


    protected final String mm7Version = "5.3.0";
    protected final String messageClass = "Personal";
    protected final String deliveryReport = "True";
    protected final String readReply = "False";
    protected final String priority = "Normal";
    protected final String distributionIndicator = "False";

    public SktSoapMessage() throws MCMPSoapRenderException {
        super();
    }
}

