package kr.co.seoultel.message.mt.mms.core.messages.direct.lgt;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.messages.direct.ktf.KtfProtocol;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

@Slf4j
@Getter
@Setter
@ToString
public class LgtDeliveryReportResMessage extends LgtSoapMessage {

    protected String tid;
    protected String statusCode;
    protected String statusText;
    protected String messageId;

    public LgtDeliveryReportResMessage() throws SOAPException {}



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
        soapHeader.addHeaderElement(new QName(Constants.LGT_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                .addTextNode(tid)
                .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement deliveryReportRsp = soapBody.addBodyElement(new QName(Constants.LGT_TRANSACTION_ID_URL, KtfProtocol.DELIVERY_REPORT_RES, "mm7"));

        SOAPElement status = deliveryReportRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

        deliveryReportRsp.addChildElement("MM7Version").addTextNode("5.3.0");

        return soapMessage;
    }


    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();

        SOAPElement transactionIdElement = (SOAPElement) soapHeader.getChildElements(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7")).next();
        this.tid = transactionIdElement != null ? transactionIdElement.getValue() : null;

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:DeliveryReportRsp element
        Element submitRspElement = (Element) document.getElementsByTagName("mm7:DeliveryReportRsp").item(0);

        // Extract values from mm7:DeliveryReportRsp element
        this.statusCode = getElementValue(submitRspElement, "StatusCode");
        this.statusText = getElementValue(submitRspElement, "StatusText");
        this.messageId = getElementValue(submitRspElement, "MessageID");
    }
}
