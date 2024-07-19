package kr.co.seoultel.message.mt.mms.core.messages.direct;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToSOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.Message;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class SoapMessage extends Message implements ConvertableToSOAPMessage {

    protected final transient MessageFactory messageFactory;

    public SoapMessage() throws MCMPSoapRenderException {
        try {
            this.messageFactory = MessageFactory.newInstance();
        } catch (SOAPException e) {
            throw new MCMPSoapRenderException("[SOAP] Fail to create soap factory", e);
        }
    }


    // Helper method to extract text content of an element by tag name
    public String getElementValue(Element parentElement, String tagName) throws MCMPSoapRenderException {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        } else {
            throw new MCMPSoapRenderException(String.format("[SOAP] Fail to find tag[%s] in element[%s]", tagName, parentElement), null);
        }
    }

    public String convertSOAPMessageToString() throws MCMPSoapRenderException {
        try {
            SOAPMessage soapMessage = toSOAPMessage();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMessage.writeTo(baos);

            return baos.toString();
        } catch (Exception e) {
            throw new MCMPSoapRenderException("[SOAP] Fail to create soap message", e);
        }
    }

    public SOAPMessage fromXml(String xml) throws MCMPSoapRenderException {
        try {
            SOAPMessage soapMessage = messageFactory.createMessage((MimeHeaders) null, new ByteArrayInputStream(xml.getBytes()));
            fromSOAPMessage(soapMessage);

            return soapMessage;
        } catch (Exception e) {
            throw new MCMPSoapRenderException("[SOAP] Fail to create soap message", e);
        }
    }
}
