package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistDeliveryAckMessage extends HistMessage {

    protected String result = "";
    protected String daAddr = "";
    protected String serial = "";

    public HistDeliveryAckMessage() {
        super(HistProtocol.HIST_DELIVERY_ACK_HEAD_TYPE, HistProtocol.HIST_DELIVERY_ACK_MSG_LENG);
    }

    @Builder
    public HistDeliveryAckMessage(String id, String pwd, String version, String keypos) {
        super(HistProtocol.HIST_DELIVERY_ACK_HEAD_TYPE, HistProtocol.HIST_DELIVERY_ACK_MSG_LENG);

        this.result = Objects.requireNonNullElse(id, "");
        this.daAddr = Objects.requireNonNullElse(pwd, "");
        this.serial = Objects.requireNonNullElse(version, "");
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, HistProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(daAddr, HistProtocol.DA_ADDR_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(serial, HistProtocol.SERIAL_LENGTH));
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        this.result = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.RESULT_LENGTH);
        this.daAddr = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.DA_ADDR_LENGTH);
        this.serial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SERIAL_LENGTH);
    }

    @Override
    public String toString() {
        return "HistDeliveryAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", daAddr='" + daAddr + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistDeliveryAckMessage that = (HistDeliveryAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(daAddr, that.daAddr) && Objects.equals(serial, that.serial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, daAddr, serial);
    }
}
