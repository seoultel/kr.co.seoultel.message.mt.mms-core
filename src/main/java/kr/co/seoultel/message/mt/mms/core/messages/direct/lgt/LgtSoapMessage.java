package kr.co.seoultel.message.mt.mms.core.messages.direct.lgt;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@ToString
public abstract class LgtSoapMessage extends SoapMessage {

    public LgtSoapMessage() throws MCMPSoapRenderException {
        super();
    }
}

