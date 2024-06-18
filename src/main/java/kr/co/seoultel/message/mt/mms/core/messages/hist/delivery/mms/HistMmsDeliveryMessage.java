package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.mms;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.delivery.HistDeliveryMultipartData;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class HistMmsDeliveryMessage extends HistDeliveryMessage {
    protected HistDeliveryMultipartData[] media = new HistDeliveryMultipartData[4];             // 첨부 파일(가변 필드)


    public HistMmsDeliveryMessage() {
        this.msgType = HistProtocol.MMS_MSG_TYPE;
    }


    public HistMmsDeliveryMessage(String daAddr, String callback, String encoding, String text, String serial, String senderCode) {
        this.msgType = HistProtocol.MMS_MSG_TYPE;
        this.daAddr = Objects.requireNonNullElse(daAddr, "");
        this.callback = Objects.requireNonNullElse(callback, "");
        this.encoding = Objects.requireNonNullElse(encoding, "");
        this.text = Objects.requireNonNullElse(text, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.senderCode = Objects.requireNonNullElse(senderCode, "");
        this.mediaCnt = 0;
    }

    public void addMedia(HistDeliveryMultipartData multipartData) {
        media[mediaCnt] = multipartData;
        this.mediaCnt += 1;
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        super.writeBody(byteBuf);

        System.out.println(this);
        for (int i = 0; i < mediaCnt; i++) {
            media[i].toByteBuf(byteBuf);
        }
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        for (int i = 0; i < mediaCnt; i++) {
            HistDeliveryMultipartData multipartData = new HistDeliveryMultipartData();
            multipartData.fromByteBuf(byteBuf);

            media[i] = multipartData;
        }
    }

    @Override
    public String toString() {
        return "HistMmsDeliveryMessage{" +
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
                ", media=" + Arrays.toString(media) +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistMmsDeliveryMessage that = (HistMmsDeliveryMessage) object;
        return Arrays.equals(media, that.media);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(media);
        return result;
    }
}
