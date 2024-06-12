package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.sms;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;

import java.util.Objects;

public class HistSmsDeliveryMessage extends HistDeliveryMessage {
    public HistSmsDeliveryMessage() {
        this.msgType = HistProtocol.SMS_MSG_TYPE;
    }


    @Builder
    public HistSmsDeliveryMessage(String msgType, String daAddr, String callback, String encoding, String text, String serial, String senderCode, int mediaCnt, String extSize) {
        this.msgType = Objects.requireNonNullElse(msgType, HistProtocol.SMS_MSG_TYPE);
        this.daAddr = Objects.requireNonNullElse(daAddr, "");
        this.callback = Objects.requireNonNullElse(callback, "");
        this.encoding = Objects.requireNonNullElse(encoding, "");
        this.text = Objects.requireNonNullElse(text, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.senderCode = Objects.requireNonNullElse(senderCode, "");
        this.mediaCnt = mediaCnt;
        this.extSize = Objects.requireNonNullElse(extSize, "");
    }
}
