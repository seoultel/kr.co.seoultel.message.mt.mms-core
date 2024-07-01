package kr.co.seoultel.message.mt.mms.core.markup.element;

import kr.co.seoultel.message.mt.mms.core.markup.attribute.Attribute;

public class Element {
    protected final StringBuilder sb = new StringBuilder();
    protected final Attribute attribute = new Attribute();

    protected String tag;

    public Element(String tag) {
        this.tag = tag;
    }

    public void addAttribute(String key, String value) {
        attribute.add(key, value);
    }


    public String toString() {
        sb.append(String.format("<%s%s>", tag, attribute.toString()));
        sb.append(String.format("</%s>", tag));
        return sb.toString();
    }
}
