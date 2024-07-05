package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.messages.direct.ktf.KtfProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.awt.image.DataBuffer;
import java.util.Objects;

@Slf4j
@Getter
public class SktSubmitResMessage extends SktSoapMessage {

    protected String tid;
    protected String statusCode;
    protected String statusText;
    protected String messageId;


    public SktSubmitResMessage() throws SOAPException {
    }

    @Builder
    public SktSubmitResMessage(String tid, String statusCode, String statusText, String messageId) throws SOAPException {
        this.tid = tid;
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.messageId = messageId;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        // Create SOAP message
        SOAPMessage soapMessage = messageFactory.createMessage();
        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "euc-kr");

        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.setPrefix("env");

        /* SOAP Header */
        SOAPHeader soapHeader = envelope.getHeader();
        soapHeader.setPrefix("env");
        soapHeader.addHeaderElement(new QName(Constants.SKT_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                .addTextNode(tid)
                .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement submitRsp = soapBody.addBodyElement(new QName(Constants.SKT_TRANSACTION_ID_URL, SktProtocol.SUBMIT_RES, "mm7"));
        submitRsp.addChildElement("MM7Version").addTextNode("5.3.0");

        SOAPElement status = submitRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

        submitRsp.addChildElement("MessageID").addTextNode(messageId);

        return soapMessage;
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();

        SOAPElement transactionIdElement = (SOAPElement) soapHeader.getChildElements(new QName(Constants.SKT_TRANSACTION_ID_URL, "TransactionID", "mm7")).next();
        this.tid = transactionIdElement != null ? transactionIdElement.getValue() : null;

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element submitRspElement = (Element) document.getElementsByTagName(String.format("mm7:%s", SktProtocol.SUBMIT_RES)).item(0);

        // Extract values from mm7:SubmitReq element
        this.statusCode = getElementValue(submitRspElement, "StatusCode");
        this.statusText = getElementValue(submitRspElement, "StatusText");
        this.messageId = getElementValue(submitRspElement, "MessageID");
    }
}
