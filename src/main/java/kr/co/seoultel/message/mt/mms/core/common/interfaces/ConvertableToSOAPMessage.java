package kr.co.seoultel.message.mt.mms.core.common.interfaces;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public interface ConvertableToSOAPMessage {

    SOAPMessage toSOAPMessage() throws SOAPException;
    void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException;
}
