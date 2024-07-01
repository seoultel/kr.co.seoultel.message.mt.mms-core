package kr.co.seoultel.message.mt.mms.core.markup;

import kr.co.seoultel.message.mt.mms.core.markup.element.Element;
import kr.co.seoultel.message.mt.mms.core.markup.element.ParentElement;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ToString
class Body {

    protected List<Element> childs = new ArrayList();


    public void addElement(Element element) {
        childs.add(element);
    }

    public void addElements(Element... elements) {
        childs.addAll(Arrays.asList(elements));
    }

    public void addElement(ParentElement smilDivElement) {
        childs.add(smilDivElement);
    }

    public void addElements(ParentElement... smilDivElements) {
        childs.addAll(Arrays.asList(smilDivElements));
    }
}
