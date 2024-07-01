package kr.co.seoultel.message.mt.mms.core.messages.direct.skt;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.namespace.QName;

import jakarta.mail.internet.MimeMultipart;
import jakarta.xml.soap.*;

import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import kr.co.seoultel.message.mt.mms.core.common.constant.Constants;
import lombok.Builder;


public class SktSubmitReqMessage extends SktSoapMessage {

    protected String tid;

    protected String callback;
    protected String receiver;

    protected String subject;
    protected String message;

    protected String vaspId;
    protected String vasId;
    protected String cpid;

    protected String resellerCode;

    protected final List<String> contentIds = List.of("1", "2", "3");



    public SktSubmitReqMessage() throws SOAPException {
        super(SktProtocol.SUBMIT_REQ);
    }

    @Builder
    public SktSubmitReqMessage(String vaspId, String vasId, String cpid,
                               String tid, String callback, String receiver, String subject, String message, String resellerCode) throws SOAPException {
        super(SktProtocol.SUBMIT_REQ);

        this.tid = tid;
        this.callback = callback;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.vaspId = vaspId;
        this.vasId = vasId;
        this.cpid = cpid;
        this.resellerCode = resellerCode;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws SOAPException {
        /* Create SOAP message */
        SOAPMessage soapMessage = messageFactory.createMessage();
        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, Boolean.TRUE.toString());
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "euc-kr");

        /* SOAP Part */
        SOAPPart soapPart = soapMessage.getSOAPPart();
        soapPart.setContentId(Constants.SKT_CONTENT_ID);

        /* SOAP Envelope */
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration("SOAP-ENV");
        envelope.setPrefix("env");

        /* SOAP Header */
        SOAPHeader soapHeader = envelope.getHeader();
        // var transactionIdText = !"SKT".equalsIgnoreCase(telecom) ? "23088" : "";
        soapHeader.setPrefix("env");
        soapHeader.addHeaderElement(new QName(Constants.SKT_TRANSACTION_ID_URL, "TransactionID", "mm7"))
                .addTextNode(tid).setAttribute("env:mustUnderstand", "1");

        /* SOAP Body */
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("env");

        SOAPBodyElement submitReq = soapBody.addBodyElement(new QName(Constants.SKT_TRANSACTION_ID_URL, localPart));
        submitReq.addChildElement("MM7Version").addTextNode(mm7Version);

        /* */
        SOAPElement senderIdentification = submitReq.addChildElement("SenderIdentification");
        senderIdentification.addChildElement("VASPID").addTextNode(vaspId);
        senderIdentification.addChildElement("VASID").addTextNode(vasId);
        senderIdentification.addChildElement("X-SKT-BPID").addTextNode(cpid);
        senderIdentification.addChildElement("X-SKT-ORIG-BPID").addTextNode(resellerCode);
        senderIdentification.addChildElement("X-SKT-RELAY-BPID").addTextNode("0009"); // 문자 중계 사업자 ID (KISA 스팸신고 규격의 문자 중계사 번호)
        senderIdentification.addChildElement("SenderAddress").addTextNode(callback);

        /* Recipients */
        SOAPElement recipients = submitReq.addChildElement("Recipients");
        recipients.addChildElement("To")
                  .addChildElement("Number")
                  .addTextNode(receiver);

        submitReq.addChildElement("ServiceCode");
        submitReq.addChildElement("LinkedID");
        submitReq.addChildElement("MessageClass").addTextNode(messageClass);
        submitReq.addChildElement("TimeStamp").addTextNode(DateUtil.getDate(0));
        submitReq.addChildElement("ExpiryDate").addTextNode(DateUtil.getDate((int) (Constants.DAY * 7)));
        submitReq.addChildElement("DeliveryReport").addTextNode(deliveryReport);
        submitReq.addChildElement("ReadReply").addTextNode(readReply);
        submitReq.addChildElement("Priority").addTextNode(priority);
        submitReq.addChildElement("DistributionIndicator").addTextNode(distributionIndicator);

// 5. X-SKT
        SOAPElement xSktElement = submitReq.addChildElement(new QName("http://vmg.nate.com:8080/soap/skt-schema.xsd", "X-SKT", "X-SKT"));
        xSktElement.addChildElement("X-SKT-Alias").addTextNode(callback);

        submitReq.addChildElement("Subject").addTextNode(subject);
        submitReq.addChildElement("Content")
                .addAttribute(new QName("allowAdaptations"), "True")
                .addAttribute(new QName("href"), "cid:" + contentIds.get(0));

        return soapMessage;
    }

    public void getSmilMultipart() {
        StringBuilder sb = new StringBuilder(1024);

        sb.append("<?xml version=\"1.0\" encoding=\"EUC-KR\"?>\r\n")
                .append("<smil xmlns=\"http://www.w3.org/2001/SMIL20/Language\">\r\n")

                /* create smil header */
                .append("<head>\r\n")
                .append("<layout>\r\n")
                .append("<root-layout background-color=\"#E7E3E7\"/>\r\n");
        sb.append("<smil xmlns=\"http://www.w3.org/2001/SMIL20/Language\">");
        /* create smil header */
        sb.append("<head>\r\n");
        sb.append("<meta name=\"mms_skt_version\" content=\"4.0\"/>\r\n");
        sb.append("</head>\r\n");

        /* create smil body */
        sb.append("<body>")
                .append("<par repeatCount=\"indefinite\">\r\n");

        sb.append("</seq></body>");
        sb.append("</smil>");
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws SOAPException {

    }


}
