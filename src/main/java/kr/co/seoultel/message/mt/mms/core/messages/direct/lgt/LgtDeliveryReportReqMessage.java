package kr.co.seoultel.message.mt.mms.core.messages.direct.lgt;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.soap.MCMPSoapRenderException;
import kr.co.seoultel.message.mt.mms.core.common.protocol.LgtProtocol;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

@Slf4j
@Getter
@ToString
public class LgtDeliveryReportReqMessage extends LgtSoapMessage {

    protected String tid;
    protected String messageId;
    protected String receiver;
    protected String callback;

    // protected String calledNet;
    protected String timeStamp;
    protected String mmStatus;

    public LgtDeliveryReportReqMessage() throws MCMPSoapRenderException {
        super();

        this.timeStamp = DateUtil.getDate();
    }

    @Builder
    public LgtDeliveryReportReqMessage(String tid, String messageId, String receiver, String callback, String mmStatus) throws MCMPSoapRenderException {
        this.tid = tid;
        this.messageId = messageId;
        this.receiver = receiver;
        this.callback = callback;
        // this.calledNet = calledNet;
        this.timeStamp = DateUtil.getDate();
        this.mmStatus = mmStatus;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws MCMPSoapRenderException {
        try {
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
            soapHeader.addHeaderElement(new QName(Constants.LGT_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                    .addTextNode(tid)
                    .setAttribute("env:mustUnderstand", "1");

            /* SOAP Body */
            SOAPBody soapBody = envelope.getBody();
            soapBody.setPrefix("env");

            SOAPBodyElement deliveryReportReq = soapBody.addBodyElement(new QName(Constants.LGT_TRANSACTION_ID_URL, LgtProtocol.DELIVERY_REPORT_REQ, "mm7"));
            deliveryReportReq.addChildElement("MM7Version").addTextNode("5.3.0");
            deliveryReportReq.addChildElement("MessageID").addTextNode(messageId);

            SOAPElement recipient = deliveryReportReq.addChildElement("Recipients");
            recipient.addChildElement("Number").addTextNode(receiver);

            SOAPElement sender = deliveryReportReq.addChildElement("Sender");
            sender.addChildElement("Number").addTextNode(callback);

            // deliveryReportReq.addChildElement("CalledNet").addTextNode(calledNet);
            deliveryReportReq.addChildElement("TimeStamp").addTextNode(timeStamp);
            deliveryReportReq.addChildElement("MMStatus").addTextNode(mmStatus);

            return soapMessage;
        } catch (Exception e) {
            throw new MCMPSoapRenderException("[SOAP] Fail to create LgtDeliveryReportReqMessage", e);
        }
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws MCMPSoapRenderException {
        try {
            SOAPHeader soapHeader = soapMessage.getSOAPHeader();
            SOAPElement transactionIdElement = (SOAPElement) soapHeader.getChildElements(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7")).next();
            this.tid = transactionIdElement != null ? transactionIdElement.getValue() : null;

            SOAPBody soapBody = soapMessage.getSOAPBody();
            Document document = soapBody.extractContentAsDocument();

            // Get mm7:SubmitReq element
            Element deliveryReportReqElement = (Element) document.getElementsByTagName("mm7:DeliveryReportReq").item(0);

            // Extract values from mm7:SubmitReq element
            this.messageId = getElementValue(deliveryReportReqElement, "MessageID");

            Element recipientsElement = (Element) document.getElementsByTagName("Recipients").item(0);
            Element recipientNumberElement = (Element) recipientsElement.getElementsByTagName("Number").item(0);
            this.receiver = recipientNumberElement.getTextContent().trim();

            Element senderElement = (Element) document.getElementsByTagName("Sender").item(0);
            Element senderNumberElement = (Element) senderElement.getElementsByTagName("Number").item(0);
            this.callback = senderNumberElement.getTextContent().trim();

            // this.calledNet = getElementValue(deliveryReportReqElement, "CalledNet");
            this.timeStamp = getElementValue(deliveryReportReqElement, "TimeStamp");
            this.mmStatus = getElementValue(deliveryReportReqElement, "MMStatus");
        } catch (Exception e) {
            throw new MCMPSoapRenderException("[SOAP] Fail to create LgtDeliveryReportReqMessage from SOAPMessage", e);
        }
    }

    public boolean isTpsOver() {
        return (mmStatus.equals(LgtProtocol.UNSUPPORTED_OPERATION) |
                mmStatus.equals(LgtProtocol.SYSTEM_ERROR) |
                mmStatus.equals(LgtProtocol.TRAFFIC_IS_OVER));
    }

    public boolean isSuccess() {
        return mmStatus.equals(LgtProtocol.SUCCESS) || mmStatus.equals(LgtProtocol.PARTIAL_SUCCESS);
    }
}
