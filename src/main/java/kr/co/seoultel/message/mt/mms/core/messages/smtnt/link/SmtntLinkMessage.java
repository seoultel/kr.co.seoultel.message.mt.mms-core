package kr.co.seoultel.message.mt.mms.core.messages.smtnt.link;

import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter @Setter
public class SmtntLinkMessage extends SmtntMessage {

    public SmtntLinkMessage() {
        super(SmtntProtocol.SMTNT_LINK_METHOD_HEADER, false);
    }


    @Override
    public String toString() {
        return "SmtntLinkMessage{" +
                "method='" + method + '\'' +
                '}';
    }
}
