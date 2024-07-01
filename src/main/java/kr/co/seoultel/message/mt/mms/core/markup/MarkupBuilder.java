package kr.co.seoultel.message.mt.mms.core.markup;

import kr.co.seoultel.message.mt.mms.core.markup.element.Element;
import lombok.ToString;

@ToString
public class MarkupBuilder {

    private final Header header = new Header();
    private final Body body = new Body();

    private final MarkupType markupType;

    public MarkupBuilder(MarkupType markupType) {
        this.markupType = markupType;
    }


    public void addElement(Element element) {
        body.addElement(element);
    }

    public void addElements(Element... elements) {
        body.addElements(elements);
    }
}
