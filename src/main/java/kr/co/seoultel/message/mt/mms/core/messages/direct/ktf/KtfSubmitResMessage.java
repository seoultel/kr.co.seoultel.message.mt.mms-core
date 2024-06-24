package kr.co.seoultel.message.mt.mms.core.messages.direct.ktf;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.messages.direct.SoapMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Objects;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class KtfSubmitResMessage extends SoapMessage {

    protected String statusCode;
    protected String statusText;
    protected String messageId;

    @Builder
    public KtfSubmitResMessage(String statusCode, String statusText, String messageId) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.messageId = messageId;
    }

    @Override
    public SOAPMessage toSOAPMessage() throws Exception {
        return null;
    }

    @Override
    public void fromSOAPMessage(SOAPMessage soapMessage) throws Exception {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        Document document = soapBody.extractContentAsDocument();

        // Get mm7:SubmitRsp element
        Element submitRspElement = (Element) document.getElementsByTagNameNS("http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-2", "SubmitRsp").item(0);

        // Extract Status elements
        Element statusElement = (Element) submitRspElement.getElementsByTagName("Status").item(0);
        this.statusCode = getElementValue(statusElement, "StatusCode");
        this.statusText = getElementValue(statusElement, "StatusText");

        // Extract other elements
        this.messageId = getElementValue(submitRspElement, "MessageID");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KtfSubmitResMessage that = (KtfSubmitResMessage) o;
        return Objects.equals(statusCode, that.statusCode) && Objects.equals(statusText, that.statusText) && Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, statusText, messageId);
    }


    @Override
    public String toString() {
        return "KtfSubmitResMessage{" +
                "statusCode='" + statusCode + '\'' +
                ", statusText='" + statusText + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
