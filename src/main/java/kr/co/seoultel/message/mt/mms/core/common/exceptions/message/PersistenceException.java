package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.mt.mms.core.messages.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersistenceException extends Exception {

    @Getter
    private Message source;

    public PersistenceException(Message source) {
        super(String.format("[SYSTEM] Broken persistency of message[%s]", source));
        this.source = source;
    }
}
