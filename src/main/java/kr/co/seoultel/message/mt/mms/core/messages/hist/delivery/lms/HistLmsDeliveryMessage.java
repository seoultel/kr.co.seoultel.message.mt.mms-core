package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.lms;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMultipartData;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistLmsDeliveryMessage extends HistDeliveryMessage {

    protected HistDeliveryMultipartData media = new HistDeliveryMultipartData();             // 첨부 파일(가변 필드)


    public HistLmsDeliveryMessage() {
        this.msgType = HistProtocol.LMS_MSG_TYPE;
    }


    public HistLmsDeliveryMessage(String msgType, String daAddr, String callback, String encoding, String text, String serial, String senderCode, String extSize, String message) {
        this.msgType = Objects.requireNonNullElse(msgType, HistProtocol.LMS_MSG_TYPE);
        this.daAddr = Objects.requireNonNullElse(daAddr, "");
        this.callback = Objects.requireNonNullElse(callback, "");
        this.encoding = Objects.requireNonNullElse(encoding, "");
        this.text = Objects.requireNonNullElse(text, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.senderCode = Objects.requireNonNullElse(senderCode, "");
        this.mediaCnt = 1;
        this.extSize = Objects.requireNonNullElse(extSize, "");
        this.media = new HistDeliveryMultipartData(message);
    }



    @Override
    protected void writeBody(ByteBuf byteBuf) {
        super.writeBody(byteBuf);

        media.toByteBuf(byteBuf);
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        media.fromByteBuf(byteBuf);
    }


    @Override
    public String toString() {
        return "HistLmsDeliveryMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", msgType='" + msgType + '\'' +
                ", daAddr='" + daAddr + '\'' +
                ", callback='" + callback + '\'' +
                ", encoding='" + encoding + '\'' +
                ", text='" + text + '\'' +
                ", serial='" + serial + '\'' +
                ", senderCode='" + senderCode + '\'' +
                ", mediaCnt=" + mediaCnt +
                ", extSize='" + extSize + '\'' +
                ", headType='" + headType + '\'' +
                ", media=" + media +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistLmsDeliveryMessage that = (HistLmsDeliveryMessage) object;
        return Objects.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), media);
    }
}
