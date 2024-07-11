package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SktProtocol;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

@Slf4j
@Getter
public class SktDeliveryReportReqMessage extends SktSoapMessage {

    protected String tid;
    protected String messageId;
    protected String receiver;
    protected String senderAddress;
    protected String timeStamp;
    protected String statusCode;
    protected String statusText;


    public SktDeliveryReportReqMessage() throws SOAPException {

    }

    @Builder
    public SktDeliveryReportReqMessage(String tid, String messageId, String receiver, String senderAddress, String statusCode, String statusText) throws SOAPException {
        this.tid = tid;
        this.messageId = messageId;

        /* Receiver, Callback */
        this.receiver = receiver;
        this.senderAddress = senderAddress;

        this.timeStamp = DateUtil.getDate(0);

        /* statusCode, statusText */
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        // Create SOAP message
        SOAPMessage soapMessage = messageFactory.createMessage();
        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "euc-kr");

        /* SOAP Part */
        SOAPPart soapPart = soapMessage.getSOAPPart();

        /* SOAP Envelope */
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

        SOAPBodyElement deliveryReportReq = soapBody.addBodyElement(new QName(Constants.SKT_TRANSACTION_ID_URL, SktProtocol.DELIVERY_REPORT_REQ, "mm7"));
        deliveryReportReq.addChildElement("MM7Version").addTextNode("5.3.0");
        deliveryReportReq.addChildElement("MessageID").addTextNode(messageId);

        SOAPElement recipient = deliveryReportReq.addChildElement("Recipient").addChildElement("Number").addTextNode(receiver);
        SOAPElement sender = deliveryReportReq.addChildElement("Sender").addTextNode(senderAddress);

        deliveryReportReq.addChildElement("CalledNet").addTextNode(timeStamp);

        SOAPElement status = deliveryReportReq.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

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
        Element deliveryReportRsqElement = (Element) document.getElementsByTagName(String.format("mm7:%s", SktProtocol.DELIVERY_REPORT_REQ)).item(0);

        // Extract values from mm7:SubmitReq element
        this.messageId = getElementValue(deliveryReportRsqElement, "MessageID");

        this.senderAddress = getElementValue(deliveryReportRsqElement, "Sender");
        this.receiver = getElementValue(deliveryReportRsqElement, "Number");

        this.statusCode = getElementValue(deliveryReportRsqElement, "StatusCode");
        this.statusText = getElementValue(deliveryReportRsqElement, "StatusText");
    }

    public boolean isTpsOver() {
        return (statusCode.equals("4006") |
                statusCode.equals("4008"));
    }

}
