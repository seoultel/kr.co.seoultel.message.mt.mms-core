package kr.co.seoultel.message.mt.mms.core.messages.hist.bind;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.hist.HistMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class HistBindMessage extends HistMessage  {

    protected String id = "";        // 인증 ID
    protected String pwd = "";       // 인증 PWD
    protected String certKey = "";   // 연동 규격 버전
    protected String type = "";    // 암호화 Key Offset
    protected String kind = "";    // 암호화 Key Offset
    protected final String version = HistProtocol.HIST_VERSION;    // 암호화 Key Offset
    protected int keypos;    // 암호화 Key Offset

    public HistBindMessage() {
        super(HistProtocol.HIST_BIND_HEAD_TYPE, HistProtocol.HIST_BIND_MSG_LENG);
    }

    @Builder
    public HistBindMessage(String id, String pwd, String certKey, String type, String kind, int keypos) {
        super(HistProtocol.HIST_BIND_HEAD_TYPE, HistProtocol.HIST_BIND_MSG_LENG);

        this.id = Objects.requireNonNullElse(id, "");
        this.pwd = Objects.requireNonNullElse(pwd, "");
        this.certKey = Objects.requireNonNullElse(certKey, "");
        this.type = Objects.requireNonNullElse(type, "");
        this.kind = Objects.requireNonNullElse(kind, "");
        this.keypos = keypos;
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(id, HistProtocol.ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(pwd, HistProtocol.PWD_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(certKey, HistProtocol.CERT_KEY_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(type, HistProtocol.TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(kind, HistProtocol.KIND_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(version, HistProtocol.VERSION_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(String.valueOf(keypos), HistProtocol.KEYPOS_LENGTH));
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
        this.certKey = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.CERT_KEY_LENGTH);
        this.type = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.TYPE_LENGTH);
        this.kind = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.KIND_LENGTH);
        byteBuf.skipBytes(HistProtocol.VERSION_LENGTH);
        this.keypos = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.KEYPOS_LENGTH);
    }

    @Override
    public String toString() {
        return "HistBindMessage{" +
                "headType='" + headType + '\'' +
                ", msgLeng=" + msgLeng +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", certKey='" + certKey + '\'' +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", version='" + version + '\'' +
                ", keypos='" + keypos + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        HistBindMessage that = (HistBindMessage) object;
        return Objects.equals(id, that.id) && Objects.equals(pwd, that.pwd) && Objects.equals(certKey, that.certKey) && Objects.equals(type, that.type) && Objects.equals(kind, that.kind) && Objects.equals(version, that.version) && Objects.equals(keypos, that.keypos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, pwd, certKey, type, kind, version, keypos);
    }
}
