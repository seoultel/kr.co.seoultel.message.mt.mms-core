package kr.co.seoultel.message.mt.mms.core.messages.smtnt;

import kr.co.seoultel.message.mt.mms.core.messages.Message;
import lombok.Getter;

import java.util.Objects;


public abstract class SmtntMessage extends Message {

    @Getter
    protected transient String method;

    protected transient boolean hasBody;
    public boolean hasBody() {
        return hasBody;
    }


    public SmtntMessage(String method) {
        this.method = method;
        this.hasBody = true;
    }

    public SmtntMessage(String method, boolean hasBody) {
        this.method = method;
        this.hasBody = hasBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof SmtntMessage)) return false;
        SmtntMessage that = (SmtntMessage) o;
        return hasBody == that.hasBody && Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, hasBody);
    }
}

