package kr.co.seoultel.message.mt.mms.core.markup.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParentElement extends Element {

    protected List<Element> childs = new ArrayList<>();

    public ParentElement(String tag) {
        super(tag);
    }


    public void addElement(Element element) {
        childs.add(element);
    }

    public void addElements(Element... elements) {
        childs.addAll(Arrays.asList(elements));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("<%s %s>", tag, attribute.toString()));
        for (Element element : childs) {
            sb.append(element.toString());
        }

        sb.append(String.format("</%s>", tag));
        return sb.toString();
    }
}
