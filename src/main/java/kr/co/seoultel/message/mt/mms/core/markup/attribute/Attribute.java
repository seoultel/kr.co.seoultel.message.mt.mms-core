package kr.co.seoultel.message.mt.mms.core.markup.attribute;

import java.util.HashMap;
import java.util.Map;

public class Attribute {

    private StringBuilder sb = new StringBuilder();
    protected final Map<String, String> attributes = new HashMap<>();

    public void add(String key, String value) {
        attributes.put(key, value);
    }

    public void remove(String key, String value) {
        attributes.remove(key);
    }

    public String toString() {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(String.format(" %s=\"%s\"", key, value));
        }

        return sb.toString();
    }
}
