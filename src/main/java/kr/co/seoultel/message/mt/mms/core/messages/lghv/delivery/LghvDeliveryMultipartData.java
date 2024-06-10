package kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery;

import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;


@Slf4j
@Getter
@NoArgsConstructor
public class LghvDeliveryMultipartData implements ConvertableToByteBuf {
    private String temp1;

    private String dataType;
    private String fileName;
    private String fileKey;
    private int dataSize;
    private String fileSize;

    private String message;

    @ToString.Exclude
    private byte[] imageBytes;

    @Builder
    public LghvDeliveryMultipartData(String temp1, String dataType, String fileName, String fileKey, int dataSize, String fileSize, String message, byte[] imageBytes) {
        this.temp1 = temp1;
        this.dataType = dataType;
        this.fileName = fileName;
        this.fileKey = fileKey;
        this.dataSize = dataSize;
        this.fileSize = fileSize;
        this.message = message;
        this.imageBytes = imageBytes;
    }

    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(null, LghvProtocol.DELIVERY_TEMP1_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(dataType, LghvProtocol.DATATYPE_LENGTH));


        switch (LghvDeliveryMultipartType.getDataType(dataType)) {
            case TEXT:
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(null, LghvProtocol.FILE_NAME_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(null, LghvProtocol.FILE_KEY_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(dataSize, LghvProtocol.DATA_SIZE_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(null, LghvProtocol.FILE_SIZE_LENGTH));
                byteBuf.writeBytes(message.getBytes(Charset.forName(EUC_KR)));
                break;

            case IMAGE:
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(fileName, LghvProtocol.FILE_NAME_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(fileKey, LghvProtocol.FILE_KEY_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(imageBytes.length, LghvProtocol.DATA_SIZE_LENGTH));
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(imageBytes.length, LghvProtocol.FILE_SIZE_LENGTH));
                byteBuf.writeBytes(imageBytes);
                break;

            case KKO_BTN:
                break;
            case ETC:
                break;
        }
    }

    @Override
    public void fromByteBuf(ByteBuf byteBuf) {
        // Body
        this.temp1 = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DELIVERY_TEMP1_LENGTH);
        this.dataType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DATATYPE_LENGTH);

        this.fileName = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.FILE_NAME_LENGTH);
        this.fileKey = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.FILE_KEY_LENGTH);
        this.dataSize = Integer.parseInt(ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DATA_SIZE_LENGTH));
        this.fileSize = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.FILE_SIZE_LENGTH);

        switch (LghvDeliveryMultipartType.getDataType(dataType)) {
            case TEXT:
                this.message = byteBuf.readBytes(dataSize).toString();
                break;
            case IMAGE:
                byteBuf.readBytes(byteBuf.readableBytes());
                this.imageBytes = new byte[dataSize];
                // this.imageBytes = byteBuf.readBytes(dataSize).array(); -> java.lang.UnsupportedOperationException: direct buffer
                break;
        }
    }


    public static LghvDeliveryMultipartData createTextMultipartData(String text) {
        byte[] bytesOfText = text.getBytes(Charset.forName(EUC_KR));
        return LghvDeliveryMultipartData.builder()
                .temp1(null)
                .dataType(LghvDeliveryMultipartType.TEXT.getDataType())
                .fileName(null)
                .fileKey(null)
                .dataSize(bytesOfText.length)
                .fileSize(null)
                .message(text)
                .build();
    }


    public static LghvDeliveryMultipartData createImageMultipartData(String fileName, String imageId, byte[] imageByteArr) {
        return LghvDeliveryMultipartData.builder()
                .dataType(LghvDeliveryMultipartType.IMAGE.getDataType())
                .fileName(fileName)
//                .fileKey(imageId.substring(0, 11)) // fileKey 중복 가능성
                .fileKey(null) // fileKey 중복 가능성
                .dataSize(imageByteArr.length)
                .fileSize(String.valueOf(imageByteArr.length))
                .imageBytes(imageByteArr)
                .build();
    }

    @Override
    public String toString() {
        return "LghvDeliveryMultipartData{" +
                "temp1='" + temp1 + '\'' +
                ", dataType='" + dataType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileKey='" + fileKey + '\'' +
                ", dataSize=" + dataSize +
                ", fileSize='" + fileSize + '\'' +
                ", message='" + message + '\'' +
                ", imageBytes.length=" + imageBytes.length +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvDeliveryMultipartData that = (LghvDeliveryMultipartData) object;
        return dataSize == that.dataSize && Objects.equals(temp1, that.temp1) && Objects.equals(dataType, that.dataType) && Objects.equals(fileName, that.fileName) && Objects.equals(fileKey, that.fileKey) && Objects.equals(fileSize, that.fileSize) && Objects.equals(message, that.message) && Arrays.equals(imageBytes, that.imageBytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(temp1, dataType, fileName, fileKey, dataSize, fileSize, message);
        result = 31 * result + Arrays.hashCode(imageBytes);
        return result;
    }
}

// dataCnt 만큼 반복 생성.
