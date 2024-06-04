package kr.co.seoultel.message.mt.mms.core.common.exceptions.message;

import kr.co.seoultel.message.mt.mms.core.messages.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersistenceException extends Exception {
    private Message message;

    public PersistenceException(Message message) {
        super(String.format("[SYSTEM] Broken persistency of message[%s]", message));
        this.message = message;
    }
}
