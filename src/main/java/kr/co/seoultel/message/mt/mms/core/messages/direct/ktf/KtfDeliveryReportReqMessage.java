package kr.co.seoultel.message.mt.mms.core.messages.direct.ktf;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
public class KtfDeliveryReportReqMessage extends KtfSoapMessage {

    protected String tid;
    protected String messageId;
    protected String receiver;
    protected String callback;
    protected String timeStamp;
    protected String mmStatus;




    public KtfDeliveryReportReqMessage() throws SOAPException {
        super();

        this.localPart = KtfProtocol.DELIVERY_REPORT_REQ;
        this.timeStamp = DateUtil.getDate();
    }

    @Builder
    public KtfDeliveryReportReqMessage(String tid, String messageId, String receiver, String callback, String timeStamp, String mmStatus) throws SOAPException {
        this.localPart = KtfProtocol.DELIVERY_REPORT_REQ;

        this.tid = tid;
        this.messageId = messageId;
        this.receiver = receiver;
        this.callback = callback;
        this.timeStamp = DateUtil.getDate();
        this.mmStatus = mmStatus;
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
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                    .addTextNode(tid)
                    .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        /*
         * TODO : KTF-TRANSACTION_ID_URL 수정 필요할지도 모름
         *        NOW : "http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-2"
         *            -> TO BE ? http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-0
         */

        SOAPBodyElement deliveryReportReq = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "DeliveryReportReq", "mm7"));
        deliveryReportReq.addChildElement("MM7Version").addTextNode(mm7Version);
        deliveryReportReq.addChildElement("MessageID").addTextNode(messageId);

        SOAPElement recipient = deliveryReportReq.addChildElement("Recipient");
        recipient.addChildElement("Number").addTextNode(receiver);

        SOAPElement sender = deliveryReportReq.addChildElement("Sender");
        sender.addChildElement("Number").addTextNode(callback);

        deliveryReportReq.addChildElement("TimeStamp").addTextNode(timeStamp);
        deliveryReportReq.addChildElement("MMStatus").addTextNode(mmStatus);

        return soapMessage;
    }


    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPElement transactionIdElement = (SOAPElement) soapHeader.getChildElements(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7")).next();
        this.tid = transactionIdElement != null ? transactionIdElement.getValue() : null;

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element deliveryReportReqElement = (Element) document.getElementsByTagName("mm7:DeliveryReportReq").item(0);

        // Extract values from mm7:SubmitReq element
        this.messageId = getElementValue(deliveryReportReqElement, "MessageID");

        Element recipientElement = (Element) document.getElementsByTagName("Recipient").item(0);
        Element recipientNumberElement = (Element) recipientElement.getElementsByTagName("Number").item(0);
        this.receiver = recipientNumberElement.getTextContent().trim();

        Element senderElement = (Element) document.getElementsByTagName("Sender").item(0);
        Element senderNumberElement = (Element) senderElement.getElementsByTagName("Number").item(0);
        this.callback = senderNumberElement.getTextContent().trim();

        this.timeStamp = getElementValue(deliveryReportReqElement, "TimeStamp");
        this.mmStatus = getElementValue(deliveryReportReqElement, "MMStatus");
    }



    public boolean isTpsOver() {
        return this.mmStatus.equals(KtfProtocol.KTF_REPORT_HUB_OVER_INTRAFFIC);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        KtfDeliveryReportReqMessage that = (KtfDeliveryReportReqMessage) object;
        return Objects.equals(tid, that.tid) && Objects.equals(messageId, that.messageId) && Objects.equals(receiver, that.receiver) && Objects.equals(callback, that.callback) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(mmStatus, that.mmStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, messageId, receiver, callback, timeStamp, mmStatus);
    }

    @Override
    public String toString() {
        return "KtfDeliveryReportReqMessage{" +
                "tid='" + tid + '\'' +
                ", messageId='" + messageId + '\'' +
                ", receiver='" + receiver + '\'' +
                ", callback='" + callback + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", mmStatus='" + mmStatus + '\'' +
                ", localPart='" + localPart + '\'' +
                '}';
    }
}

