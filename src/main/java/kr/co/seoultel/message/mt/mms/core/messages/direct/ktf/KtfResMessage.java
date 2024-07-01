package kr.co.seoultel.message.mt.mms.core.messages.direct.ktf;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Getter @Setter
public class KtfResMessage extends KtfSoapMessage {

    protected String tid;
    protected String statusCode;
    protected String statusText;
    protected String messageId;


    public KtfResMessage(String localPart) throws SOAPException {
        super(localPart);
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        switch (localPart) {
            case KtfProtocol.SUBMIT_RES:
                return createSOAPMessageByKtfSubmitResMessage();

            case KtfProtocol.DELIVERY_REPORT_RES:
                return createSOAPMessageByKtfDeliveryReportResMessage();

            case KtfProtocol.RS_ERROR_RES:
                return createSOAPMessageByKtfRsErrorResMessage();

            case KtfProtocol.VASP_ERROR_RES:
            default:
                return createSOAPMessageByKtfVaspErrorResMessage();
        }
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        switch (localPart) {
            case KtfProtocol.SUBMIT_RES:
                injectFieldsByKtrSubmitResMessageFromSOAPMessage(soapMessage);
                break;

            case KtfProtocol.DELIVERY_REPORT_RES:
                injectFieldsByKtrDeliveryReportResMessageFromSOAPMessage(soapMessage);
                break;


            case KtfProtocol.RS_ERROR_RES:
            case KtfProtocol.VASP_ERROR_RES:
                injectFieldsByKtrErrorMessageFromSOAPMessage(soapMessage);
                break;
        }
    }


    public SOAPMessage createSOAPMessageByKtfSubmitResMessage() throws SOAPException {
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
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                  .addTextNode(tid)
                  .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement submitRsp = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, KtfProtocol.SUBMIT_RES, "mm7"));

        SOAPElement status = submitRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

        submitRsp.addChildElement("MM7Version").addTextNode(mm7Version);
        submitRsp.addChildElement("MessageID").addTextNode(messageId);

        return soapMessage;
    }


    public SOAPMessage createSOAPMessageByKtfDeliveryReportResMessage() throws SOAPException {
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
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                  .addTextNode(tid)
                  .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement deliveryReportRsp = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, KtfProtocol.DELIVERY_REPORT_RES, "mm7"));

        SOAPElement status = deliveryReportRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

        deliveryReportRsp.addChildElement("MM7Version").addTextNode(mm7Version);

        return soapMessage;
    }


    public SOAPMessage createSOAPMessageByKtfVaspErrorResMessage() throws SOAPException {
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
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                  .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement rsErrorRsp = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, KtfProtocol.VASP_ERROR_RES, "mm7"));
        rsErrorRsp.addChildElement("MM7Version").addTextNode(mm7Version);

        SOAPElement status = rsErrorRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);

        return soapMessage;
    }

    public SOAPMessage createSOAPMessageByKtfRsErrorResMessage() throws SOAPException {
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
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                  .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement rsErrorRsp = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, KtfProtocol.RS_ERROR_RES, "mm7"));
        rsErrorRsp.addChildElement("MM7Version").addTextNode(mm7Version);

        SOAPElement status = rsErrorRsp.addChildElement("Status");
        status.addChildElement("StatusCode").addTextNode(statusCode);
        status.addChildElement("StatusText").addTextNode(statusText);


        return soapMessage;
    }

    public void injectFieldsByKtrSubmitResMessageFromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPElement transactionIdElement = (SOAPElement) soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"));
        this.tid = transactionIdElement.getValue();

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element submitRspElement = (Element) document.getElementsByTagName("mm7:SubmitRsp").item(0);

        // Extract values from mm7:SubmitReq element
        this.statusCode = getElementValue(submitRspElement, "StatusCode");
        this.statusText = getElementValue(submitRspElement, "StatusText");
        this.messageId = getElementValue(submitRspElement, "MessageID");
    }

    public void injectFieldsByKtrDeliveryReportResMessageFromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPElement transactionIdElement = (SOAPElement) soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"));
        this.tid = transactionIdElement.getValue();

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element submitRspElement = (Element) document.getElementsByTagName("mm7:SubmitRsp").item(0);

        // Extract values from mm7:SubmitReq element
        this.statusCode = getElementValue(submitRspElement, "StatusCode");
        this.statusText = getElementValue(submitRspElement, "StatusText");
        this.messageId = getElementValue(submitRspElement, "MessageID");
    }

    public void injectFieldsByKtrErrorMessageFromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPElement transactionIdElement = (SOAPElement) soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"));
        this.tid = transactionIdElement.getValue();

        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element submitRspElement = (Element) document.getElementsByTagName("mm7:SubmitRsp").item(0);

        // Extract values from mm7:SubmitReq element
        this.statusCode = getElementValue(submitRspElement, "StatusCode");
        this.statusText = getElementValue(submitRspElement, "StatusText");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        KtfResMessage that = (KtfResMessage) object;
        return Objects.equals(tid, that.tid) && Objects.equals(statusCode, that.statusCode) && Objects.equals(statusText, that.statusText) && Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, statusCode, statusText, messageId);
    }

    @Override
    public String toString() {
        return "KtfResMessage{" +
                "tid='" + tid + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", statusText='" + statusText + '\'' +
                ", messageId='" + messageId + '\'' +
                ", localPart='" + localPart + '\'' +
                '}';
    }
}
