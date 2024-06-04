package kr.co.seoultel.message.mt.mms.core.messages.smtnt;

import kr.co.seoultel.message.mt.mms.core.messages.Message;
import lombok.Getter;


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

}

