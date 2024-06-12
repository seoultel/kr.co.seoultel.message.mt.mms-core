package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistDeliveryMessage extends HistMessage {

    protected String msgType = "";           // 메세지 타입
    protected String daAddr = "";            // 착신번호
    protected String callback = "";          // 회신번호
    protected String encoding = "";          // 인코딩 타입
    protected String text = "";              // 메세지 타입
    protected String serial = "";            // Serial Number
    protected String senderCode = "";        // 최초 발신사업자 식별 코드
    protected int mediaCnt = 0;              // 첨부 파일 개수
    protected String extSize = "";           // 확장 필드 크기


    public HistDeliveryMessage() {
        super(HistProtocol.HIST_DELIVERY_HEAD_TYPE, HistProtocol.HIST_DELIVERY_MSG_LENG);
    }


    public HistDeliveryMessage(String msgType, String daAddr, String callback, String encoding, String text, String serial, String senderCode, int mediaCnt, String extSize) {
        super(HistProtocol.HIST_DELIVERY_HEAD_TYPE, HistProtocol.HIST_DELIVERY_MSG_LENG);

        this.msgType = Objects.requireNonNullElse(msgType, "");
        this.daAddr = Objects.requireNonNullElse(daAddr, "");
        this.callback = Objects.requireNonNullElse(callback, "");
        this.encoding = Objects.requireNonNullElse(encoding, "");
        this.text = Objects.requireNonNullElse(text, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.senderCode = Objects.requireNonNullElse(senderCode, "");
        this.mediaCnt = mediaCnt;
        this.extSize = Objects.requireNonNullElse(extSize, "");
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgType, HistProtocol.MSG_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(daAddr, HistProtocol.DA_ADDR_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(callback, HistProtocol.CALLBACK_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(encoding, HistProtocol.ENCODING_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(text, HistProtocol.TEXT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(serial, HistProtocol.SERIAL_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(senderCode, HistProtocol.SENDER_CODE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(mediaCnt, HistProtocol.MEDIA_CNT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(extSize, HistProtocol.EXT_SIZE_LENGTH));
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        this.msgType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.MSG_TYPE_LENGTH);
        this.daAddr = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.DA_ADDR_LENGTH);
        this.callback = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.CALLBACK_LENGTH);
        this.encoding = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.ENCODING_LENGTH);
        this.text = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.TEXT_LENGTH);
        this.serial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SERIAL_LENGTH);
        this.senderCode = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SENDER_CODE_LENGTH);
        this.mediaCnt = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.MEDIA_CNT_LENGTH);
        this.extSize = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.EXT_SIZE_LENGTH);
    }

    @Override
    public String toString() {
        return "HistDeliveryMessage{" +
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
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof HistDeliveryMessage)) return false;
        HistDeliveryMessage that = (HistDeliveryMessage) object;
        return mediaCnt == that.mediaCnt && Objects.equals(msgType, that.msgType) && Objects.equals(daAddr, that.daAddr) && Objects.equals(callback, that.callback) && Objects.equals(encoding, that.encoding) && Objects.equals(text, that.text) && Objects.equals(serial, that.serial) && Objects.equals(senderCode, that.senderCode) && Objects.equals(extSize, that.extSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), msgType, daAddr, callback, encoding, text, serial, senderCode, mediaCnt, extSize);
    }
}
