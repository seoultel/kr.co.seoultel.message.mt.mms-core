package kr.co.seoultel.message.mt.mms.core.messages.smtnt.link;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter @Setter
public class SmtntLinkAckMessage extends SmtntMessage {

    public SmtntLinkAckMessage() {
        super(SmtntProtocol.SMTNT_LINK_ACK_METHOD_HEADER, false);
    }


    @Override
    public String toString() {
        return "SmtntLinkMessage{" +
                "method='" + method + '\'' +
                '}';
    }


}
