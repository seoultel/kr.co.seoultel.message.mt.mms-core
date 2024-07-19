package kr.co.seoultel.message.mt.mms.core.common.interfaces;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;

public interface ConvertableToSOAPMessage {

    SOAPMessage toSOAPMessage() throws MCMPSoapRenderException;
    void fromSOAPMessage(SOAPMessage soapMessage) throws MCMPSoapRenderException;
}
