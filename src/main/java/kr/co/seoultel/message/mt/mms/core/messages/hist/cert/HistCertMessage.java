package kr.co.seoultel.message.mt.mms.core.messages.hist.cert;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistCertMessage extends HistMessage {
    protected String id = "";        // 인증 ID
    protected String pwd = "";       // 인증 PWD
    protected String version = "";   // 연동 규격 버전
    protected String keypos = "";    // 암호화 Key Offset

    public HistCertMessage() {
        super(HistProtocol.HIST_CERT_HEAD_TYPE, HistProtocol.HIST_CERT_MSG_LENG);
    }

    @Builder
    public HistCertMessage(String id, String pwd, String version, String keypos) {
        super(HistProtocol.HIST_CERT_HEAD_TYPE, HistProtocol.HIST_CERT_MSG_LENG);

        this.id = Objects.requireNonNullElse(id, "");
        this.pwd = Objects.requireNonNullElse(pwd, "");
        this.version = Objects.requireNonNullElse(version, "");
        this.keypos = Objects.requireNonNullElse(keypos, "");
    }


    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(id, HistProtocol.ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(pwd, HistProtocol.PWD_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(version, HistProtocol.VERSION_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(keypos, HistProtocol.KEYPOS_LENGTH));
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        writeHeader(byteBuf);
        writeBody(byteBuf);
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        super.fromByteBuf(byteBuf);

        this.id = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.ID_LENGTH);
        this.pwd = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.PWD_LENGTH);
        this.version = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.VERSION_LENGTH);
        this.keypos = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.KEYPOS_LENGTH);
    }

    @Override
    public String toString() {
        return "HistCertMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", version='" + version + '\'' +
                ", keypos='" + keypos + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistCertMessage that = (HistCertMessage) object;
        return Objects.equals(id, that.id) && Objects.equals(pwd, that.pwd) && Objects.equals(version, that.version) && Objects.equals(keypos, that.keypos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, pwd, version, keypos);
    }
}
