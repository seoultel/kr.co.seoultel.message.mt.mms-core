package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SktProtocol;
import lombok.Builder;
import lombok.ToString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

@ToString
public class SktDeliveryReportResMessage extends SktSoapMessage {

    protected String tid;
    protected String statusCode;
    protected String statusText;

    public SktDeliveryReportResMessage() throws SOAPException {
    }

    @Builder
    public SktDeliveryReportResMessage(String tid, String statusCode, String statusText) throws SOAPException {
        this.tid = tid;
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

        SOAPBodyElement deliveryReportReq = soapBody.addBodyElement(new QName(Constants.SKT_TRANSACTION_ID_URL, SktProtocol.DELIVERY_REPORT_RES, "mm7"));
        deliveryReportReq.addChildElement("MM7Version").addTextNode("5.3.0");

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

        this.statusCode = getElementValue(deliveryReportRsqElement, "StatusCode");
        this.statusText = getElementValue(deliveryReportRsqElement, "StatusText");
    }

}
