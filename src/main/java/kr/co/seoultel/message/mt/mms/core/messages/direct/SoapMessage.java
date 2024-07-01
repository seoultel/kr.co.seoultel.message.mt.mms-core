package kr.co.seoultel.message.mt.mms.core.messages.direct;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToSOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.Message;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class SoapMessage extends Message implements ConvertableToSOAPMessage {

    protected final transient MessageFactory messageFactory;

    public SoapMessage() throws SOAPException {
        this.messageFactory = MessageFactory.newInstance();
    }


    // Helper method to extract text content of an element by tag name
    protected String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }

    public String convertSOAPMessageToString() throws SOAPException, IOException {
        SOAPMessage soapMessage = toSOAPMessage();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        soapMessage.writeTo(baos);

        return baos.toString();
    }

    public SOAPMessage fromXml(String xml) throws SOAPException, IOException {
        SOAPMessage soapMessage = messageFactory.createMessage((MimeHeaders) null, new ByteArrayInputStream(xml.getBytes()));
        fromSOAPMessage(soapMessage);

        return soapMessage;
    }
}
