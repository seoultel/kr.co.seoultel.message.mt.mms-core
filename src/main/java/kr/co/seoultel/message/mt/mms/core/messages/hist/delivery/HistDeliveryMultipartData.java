package kr.co.seoultel.message.mt.mms.core.messages.hist.delivery;

import io.netty.buffer.ByteBufUtil;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import io.netty.buffer.ByteBuf;

import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol.M_TYPE_21;
import static kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol.M_TYPE_23;


@Getter
@NoArgsConstructor
public class HistDeliveryMultipartData implements ConvertableToByteBuf {
    protected String mType;
    protected String encoding = HistProtocol.EUC_KR_ENCODING;
    protected int mFileLen;
    protected byte[] bytes;

    public HistDeliveryMultipartData(String message) {
        byte[] bytes = message.getBytes(Charset.forName(EUC_KR));

        this.mType = HistProtocol.M_TYPE_11;
        this.encoding = HistProtocol.EUC_KR_ENCODING;
        this.mFileLen = bytes.length;
        this.bytes = bytes;
    }


    public HistDeliveryMultipartData(String extension, byte[] mediaFileBytes) throws IllegalArgumentException {
        this.mType = getMType(extension);
        this.encoding = HistProtocol.EUC_KR_ENCODING;
        this.mFileLen = mediaFileBytes.length;
        this.bytes = mediaFileBytes;
    }

    @Builder
    public HistDeliveryMultipartData(String mType, int mFileLen, byte[] media) {
        this.mType = Objects.requireNonNull(mType);
        this.encoding = HistProtocol.EUC_KR_ENCODING;
        this.mFileLen = mFileLen;
        this.bytes = media;
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(mType, HistProtocol.M_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(encoding, HistProtocol.ENCODING_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(mFileLen, HistProtocol.M_FILE_LEN_LENGTH));
        byteBuf.writeBytes(bytes);
    }

    public String getMType(String extension) throws IllegalArgumentException {
        switch (extension.toUpperCase()) {
            case "JPG":
                return M_TYPE_21;
            case "PNG":
                return M_TYPE_23;

            default:
                throw new IllegalArgumentException("지원하지 않는 extension 입니다.");
        }
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        this.mType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.M_TYPE_LENGTH);
        this.encoding = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, HistProtocol.ENCODING_LENGTH);
        this.mFileLen = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, HistProtocol.M_FILE_LEN_LENGTH);
        this.bytes = ByteBufUtil.getBytes(byteBuf, byteBuf.readerIndex(), mFileLen);
        // this.bytes = byteBuf.readBytes(mFileLen)
        // this.bytes = new byte[mFileLen]; // 센더는 MultipartData 를 읽어올 일이 없음.
    }

    @Override
    public String toString() {
        return "HistDeliveryMultipartData{" +
                "mType='" + mType + '\'' +
                ", encoding='" + encoding + '\'' +
                ", mFileLen=" + mFileLen +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HistDeliveryMultipartData that = (HistDeliveryMultipartData) object;
        return mFileLen == that.mFileLen && Objects.equals(mType, that.mType) && Objects.equals(encoding, that.encoding) && Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mType, encoding, mFileLen);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
