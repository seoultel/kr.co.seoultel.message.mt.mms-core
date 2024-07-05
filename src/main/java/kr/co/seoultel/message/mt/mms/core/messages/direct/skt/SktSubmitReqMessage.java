package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import javax.xml.namespace.QName;

import jakarta.xml.soap.*;

import kr.co.seoultel.message.mt.mms.core.util.CommonUtil;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;


@Slf4j
@Getter
@ToString
public class SktSubmitReqMessage extends SktSoapMessage {

    protected String tid;

    protected String callback;
    protected String receiver;

    protected String subject;
    protected String message;

    protected String vaspId;
    protected String vasId;
    protected String cpid;

    protected String originCode;

    protected String startCID;
    protected String smilCID;



    public SktSubmitReqMessage() throws SOAPException {
    }

    @Builder
    public SktSubmitReqMessage(String vaspId, String vasId, String cpid,
                               String tid, String callback, String receiver, String subject, String message, String originCode,
                               String startCID, String smilCID) throws SOAPException {
        this.tid = tid;
        this.callback = callback;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.vaspId = vaspId;
        this.vasId = vasId;
        this.cpid = cpid;
        this.originCode = originCode;
        this.startCID = startCID;
        this.smilCID = smilCID;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        /* Create SOAP message */
        SOAPMessage soapMessage = messageFactory.createMessage();
        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "euc-kr");

        /* SOAP Part */
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // soapPart.setContentId("<start_MM7_SOAP>");
        // soapPart.setMimeHeader("Content-ID", String.format("<%s>", startCID));
        soapPart.setContentId("<start_MM7_SOAP>");

        /* SOAP Envelope */
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.setPrefix("env");
        envelope.removeNamespaceDeclaration("SOAP-ENV");

        /* SOAP Header */
        SOAPHeader soapHeader = envelope.getHeader();
        soapHeader.setPrefix("env");

        SOAPElement transaction = soapHeader.addChildElement(new QName(Constants.SKT_TRANSACTION_ID_URL, "TransactionID", "mm7"));
        transaction.setAttribute("env:mustUnderstand", "1");
        transaction.addTextNode(this.tid);



        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement submitReq = soapBody.addBodyElement(new QName(Constants.SKT_TRANSACTION_ID_URL, SktProtocol.SUBMIT_REQ));
        submitReq.addChildElement("MM7Version").addTextNode(mm7Version);

        /* */
        SOAPElement senderIdentification = submitReq.addChildElement("SenderIdentification");
        senderIdentification.addChildElement("VASPID").addTextNode(vaspId);
        senderIdentification.addChildElement("VASID").addTextNode(vasId);
        senderIdentification.addChildElement("X-SKT-BPID").addTextNode(cpid);

        senderIdentification.addChildElement("X-SKT-RELAY-BPID").addTextNode("0009"); // 문자 중계 사업자 ID (KISA 스팸신고 규격의 문자 중계사 번호)
        senderIdentification.addChildElement("SenderAddress").addTextNode(callback);

        submitReq.addChildElement("X-SKT-ORIG-BPID").addTextNode(originCode);

        /* Recipients */
        SOAPElement recipients = submitReq.addChildElement("Recipients");
        recipients.addChildElement("To")
                  .addChildElement("Number")
                  .addTextNode(receiver);


        // submitReq.addChildElement("ServiceCode");
         submitReq.addChildElement("LinkedID");


        submitReq.addChildElement("MessageClass").addTextNode(messageClass);
        submitReq.addChildElement("TimeStamp").addTextNode(DateUtil.getZoneDateTime(0, "EEE, d MMM yyyy hh:mm:ss Z"));
        submitReq.addChildElement("ExpiryDate").addTextNode(DateUtil.getZoneDateTime((int) (Constants.DAY * 7), "EEE, d MMM yyyy hh:mm:ss Z"));
        submitReq.addChildElement("DeliveryReport").addTextNode(deliveryReport);
        submitReq.addChildElement("ReadReply").addTextNode(readReply);
        submitReq.addChildElement("Priority").addTextNode(priority);
        submitReq.addChildElement("DistributionIndicator").addTextNode(distributionIndicator);

//        // 5. X-SKT
//        SOAPElement xSktElement = submitReq.addChildElement(new QName("http://vmg.nate.com:8080/soap/skt-schema.xsd", "X-SKT", "X-SKT"));
//        xSktElement.addChildElement("X-SKT-Alias").addTextNode(callback);

        submitReq.addChildElement("Subject").addTextNode(subject);
        submitReq.addChildElement("Content")
                .addAttribute(new QName("allowAdaptations"), "True")
                .addAttribute(new QName("href"), String.format("cid:%s", smilCID));

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
        Element submitReqElement = (Element) document.getElementsByTagName(SktProtocol.SUBMIT_REQ).item(0);

        // Extract values from mm7:SubmitReq element
        this.vaspId = getElementValue(submitReqElement, "VASPID");
        this.vasId = getElementValue(submitReqElement, "VASID");
        this.cpid = getElementValue(submitReqElement, "X-SKT-BPID");
        this.callback = getElementValue(submitReqElement, "SenderAddress");
        this.receiver = getElementValue(submitReqElement, "Number");
        this.originCode = getElementValue(submitReqElement, "X-SKT-ORIG-BPID");
        this.subject = getElementValue(submitReqElement, "Subject");
    }
}
