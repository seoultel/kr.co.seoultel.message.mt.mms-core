package kr.co.seoultel.message.mt.mms.core.messages.direct;

import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToSOAPMessage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class SoapMessage implements ConvertableToSOAPMessage {


    // Helper method to extract text content of an element by tag name
    protected String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }
}
