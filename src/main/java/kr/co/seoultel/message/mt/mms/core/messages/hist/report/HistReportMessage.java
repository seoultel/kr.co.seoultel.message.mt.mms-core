package kr.co.seoultel.message.mt.mms.core.messages.hist.report;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistReportMessage extends HistMessage {

    protected String result = "";    // 결과 코드
    protected String message = "";   // 결과 메세지
    protected String daAddr = "";    // 착신 번호
    protected String serial = "";    // Serial-Number
    protected String gwSerial = "";  // G/W Serial-Number
    protected String sendTime = "";  // 전송 완료 시간
    protected String telcoInfo = ""; // 발송 이통사 정보


    public HistReportMessage() {
        super(HistProtocol.HIST_REPORT_HEAD_TYPE, HistProtocol.HIST_REPORT_MSG_LENG);
    }

    public HistReportMessage(String result, String message, String daAddr, String serial, String gwSerial, String sendTime, String telcoInfo) {
        super(HistProtocol.HIST_REPORT_HEAD_TYPE, HistProtocol.HIST_REPORT_MSG_LENG);

        this.result = Objects.requireNonNullElse(result, "");
        this.message = Objects.requireNonNullElse(message, "");
        this.daAddr = Objects.requireNonNullElse(daAddr, "");
        this.serial = Objects.requireNonNullElse(serial, "");
        this.gwSerial = Objects.requireNonNullElse(gwSerial, "");
        this.sendTime = Objects.requireNonNullElse(sendTime, "");
        this.telcoInfo = Objects.requireNonNullElse(telcoInfo, "");
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(result, HistProtocol.RESULT_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(message, HistProtocol.MESSAGE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(daAddr, HistProtocol.DA_ADDR_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(serial, HistProtocol.SERIAL_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(gwSerial, HistProtocol.GW_SERIAL_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(sendTime, HistProtocol.SEND_TIME_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(telcoInfo, HistProtocol.TELCO_INFO_LENGTH));
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
        this.message = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.MESSAGE_LENGTH);
        this.daAddr = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.DA_ADDR_LENGTH);
        this.serial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SERIAL_LENGTH);
        this.gwSerial = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.GW_SERIAL_LENGTH);
        this.sendTime = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.SEND_TIME_LENGTH);
        this.telcoInfo = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.TELCO_INFO_LENGTH);
    }

    @Override
    public String toString() {
        return "HistReportMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", daAddr='" + daAddr + '\'' +
                ", serial='" + serial + '\'' +
                ", gwSerial='" + gwSerial + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", telcoInfo='" + telcoInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistReportMessage that = (HistReportMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(message, that.message) && Objects.equals(daAddr, that.daAddr) && Objects.equals(serial, that.serial) && Objects.equals(gwSerial, that.gwSerial) && Objects.equals(sendTime, that.sendTime) && Objects.equals(telcoInfo, that.telcoInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), result, message, daAddr, serial, gwSerial, sendTime, telcoInfo);
    }
}
