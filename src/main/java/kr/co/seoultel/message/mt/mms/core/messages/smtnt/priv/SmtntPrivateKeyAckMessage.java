package kr.co.seoultel.message.mt.mms.core.messages.smtnt.priv;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter  @Setter
public class SmtntPrivateKeyAckMessage extends SmtntMessage {

    protected boolean hasBody = false;
    public SmtntPrivateKeyAckMessage() {
        super(SmtntProtocol.SMTNT_PRIVATE_KEY_ACK_METHOD_HEADER);
    }

    @Override
    public String toString() {
        return "SmtntPrivateKeyMessage{" +
                    "method='" + method + '\'' +
                '}';
    }


}
