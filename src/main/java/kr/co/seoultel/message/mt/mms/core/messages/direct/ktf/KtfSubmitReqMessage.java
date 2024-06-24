package kr.co.seoultel.message.mt.mms.core.messages.direct.ktf;

import jakarta.xml.soap.*;
import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.nio.charset.Charset;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;

@Slf4j
@ToString
public class KtfSubmitReqMessage extends SoapMessage {


    protected final transient MessageFactory messageFactory;
    protected final String serviceType = "MMSMT1";
    protected final String mm7Version = "1.0";

    protected String dstMsgId;
    protected String vaspId;
    protected String vasId;
    protected String cpid;
    protected final String senderAddress = "01025971376";
    protected String callback;
    protected String receiver;
    protected final String messageClass = "GENERAL";
    protected String timeStamp;
    protected String subject;
    protected final String deliveryReport = "TRUE";
    protected final String readReply = "TRUE";
    protected String resellerCode;


    public KtfSubmitReqMessage() throws SOAPException {
        messageFactory = MessageFactory.newInstance();
        this.timeStamp = DateUtil.getDate(0, "dd-MM-yyyy HH:mm:ss");
    }

    @Builder
    public KtfSubmitReqMessage(String vaspId, String vasId, String cpid, String dstMsgId, String callback, String receiver, String subject, String resellerCode) throws SOAPException {
        this.messageFactory = MessageFactory.newInstance();

        this.vaspId = vaspId;
        this.vasId = vasId;
        this.cpid = cpid;

        this.dstMsgId = dstMsgId;
        this.callback = callback;
        this.receiver = receiver;
        this.timeStamp = DateUtil.getDate(0, "dd-MM-yyyy HH:mm:ss");
        this.subject = subject;
        this.resellerCode = resellerCode;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {

        // Create SOAP message factory
        MessageFactory factory = MessageFactory.newInstance();

        // Create SOAP message
        SOAPMessage soapMessage = factory.createMessage();
        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "euc-kr");

        /* SOAP Part */
        SOAPPart soapPart = soapMessage.getSOAPPart();
        soapPart.setContentId(Constants.KTF_CONTENT_ID);
        soapPart.setMimeHeader("Content-Transfer-Encoding", "8bit");

        /* SOAP Envelope */
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.setPrefix("env");


        /* SOAP Header */
        SOAPHeader soapHeader = envelope.getHeader();
        soapHeader.setPrefix("env");
        soapHeader.addHeaderElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                .addTextNode(dstMsgId)
                .setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement submitReq = soapBody.addBodyElement(new QName(Constants.KTF_TRANSACTION_ID_URL, "SubmitReq", "mm7"));
        submitReq.addChildElement("ServiceType").addTextNode(serviceType);
        submitReq.addChildElement("MM7Version").addTextNode(mm7Version);

        SOAPElement senderIdentification = submitReq.addChildElement("SenderIdentification");
        senderIdentification.addChildElement("VASPID").addTextNode(vaspId);
        senderIdentification.addChildElement("VASID").addTextNode(vasId);
        senderIdentification.addChildElement("CPID").addTextNode(cpid);
        senderIdentification.addChildElement("SenderAddress").addTextNode(senderAddress);   //고정값 (과금 번호)
        senderIdentification.addChildElement("CallBack").addTextNode(callback);

        submitReq.addChildElement("Recipients")
                .addChildElement("To")
                .addChildElement("Number")
                .addTextNode(receiver);

        submitReq.addChildElement("MessageClass").addTextNode(messageClass);
        submitReq.addChildElement("TimeStamp").addTextNode(timeStamp);
        submitReq.addChildElement("Subject").addTextNode(new String(subject.getBytes(Charset.forName(EUC_KR))));
        submitReq.addChildElement("DeliveryReport").addTextNode(deliveryReport);
        submitReq.addChildElement("ReadReply").addTextNode(readReply);
        submitReq.addChildElement("ResellerCode").addTextNode(resellerCode);


        // Save changes to SOAP message
        soapMessage.saveChanges();

        return soapMessage;
    }


    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitReq element
        Element submitReqElement = (Element) document.getElementsByTagName("mm7:SubmitReq").item(0);

        // Extract values from mm7:SubmitReq element
        this.vaspId = getElementValue(submitReqElement, "VASPID");
        this.vasId = getElementValue(submitReqElement, "VASID");
        this.cpid = getElementValue(submitReqElement, "CPID");
        this.callback = getElementValue(submitReqElement, "CallBack");
        this.receiver = getElementValue(submitReqElement, "Number");
        this.timeStamp = getElementValue(submitReqElement, "TimeStamp");
        this.subject = getElementValue(submitReqElement, "Subject");
    }
}

