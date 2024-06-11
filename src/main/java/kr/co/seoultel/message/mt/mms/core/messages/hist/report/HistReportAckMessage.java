package kr.co.seoultel.message.mt.mms.core.messages.hist.report;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistReportAckMessage extends HistMessage {
    protected String result = "";    // 결과 코드

    protected String serial = "";    // Serial-Number
    protected String gwSerial = "";  // G/W Serial-Number

    public HistReportAckMessage() {
        super(HistProtocol.HIST_REPORT_ACK_HEAD_TYPE, HistProtocol.HIST_REPORT_ACK_MSG_LENG);
    }

    public HistReportAckMessage(String result, String serial, String gwSerial) {
        super(HistProtocol.HIST_REPORT_ACK_HEAD_TYPE, HistProtocol.HIST_REPORT_ACK_MSG_LENG);

        this.result = Objects.requireNonNullElse(result, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.gwSerial = Objects.requireNonNullElse(gwSerial, "");
    }



    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, HistProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(serial, HistProtocol.SERIAL_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(gwSerial, HistProtocol.GW_SERIAL_LENGTH));
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
        this.serial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SERIAL_LENGTH);
        this.gwSerial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.GW_SERIAL_LENGTH);
    }

    @Override
    public String toString() {
        return "HistReportAckMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", serial='" + serial + '\'' +
                ", gwSerial='" + gwSerial + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistReportAckMessage that = (HistReportAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(serial, that.serial) && Objects.equals(gwSerial, that.gwSerial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, serial, gwSerial);
    }
}
