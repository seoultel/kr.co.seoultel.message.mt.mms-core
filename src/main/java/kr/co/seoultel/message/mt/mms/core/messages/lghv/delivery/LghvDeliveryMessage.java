package kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.messages.lghv.LghvMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.LghvProtocol;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.EUC_KR;

@Slf4j
@Getter
public class LghvDeliveryMessage extends LghvMessage {
    private String encode;          // 인코딩 타입
    private String deliveryType;    // 전송 타입
    private String msgId;           // 메세지 고유값
    private String gubun;           // 상세구분
    private String contentsType;    // 컨텐츠 타입
    private String destAddr;        // 수신번호
    private String callback;        // 회신번호
    private String reseller;        // 식별코드
    private String subject;         // 메세지 제목

    private String adFlag;          // 친구톡 광고문구여부
    private String reserved;        // 예비 필드

    private String text;            // 메세지 내용
    private String ksubject;        // 친구톡용 제목
    private int dataCnt = 0;         // 데이터 개수(1~4)

    private LghvDeliveryMultipartData[] multipartDatas = new LghvDeliveryMultipartData[4];

    public LghvDeliveryMessage() {
        super(LghvProtocol.DELIVERY_MSG_TYPE);
    }
    @Builder
    public LghvDeliveryMessage(String encode, String deliveryType, String msgId, String gubun, String contentsType, String destAddr, String callback, String reseller, String subject,
                               String text, String adFlag, String reserved, String ksubject, LghvDeliveryMultipartData[] multipartDatas) {
        super(LghvProtocol.DELIVERY_MSG_TYPE);

        // LMS & MMS 공통 필드
        this.encode = encode;
        this.deliveryType = deliveryType;
        this.msgId = msgId;
        this.gubun = gubun;
        this.contentsType = contentsType;
        this.destAddr = destAddr;
        this.callback = callback;
        this.reseller = reseller;
        this.subject = Objects.requireNonNullElse(subject, "제목 없음");

        // LMS 필드
        this.text = text;

        // MMS 필드
        this.multipartDatas = Objects.requireNonNullElse(multipartDatas, new LghvDeliveryMultipartData[4]);
    }

    @Override
    protected void writeBody(ByteBuf byteBuf) {
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(encode, LghvProtocol.ENCODE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(deliveryType, LghvProtocol.DELIVERY_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(msgId, LghvProtocol.MSG_ID_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(gubun, LghvProtocol.GUBUN_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(contentsType, LghvProtocol.CONTENTS_TYPE_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(destAddr, LghvProtocol.DESTADDR_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(callback, LghvProtocol.CALLBACK_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(reseller, LghvProtocol.RESELLER_LENGTH));
        byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(subject, LghvProtocol.DELIVERY_SUBJECT_LENGTH));

        switch (deliveryType) {
            case LghvProtocol.LMS_DELIVERY_TYPE:

                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(text, text.getBytes(Charset.forName(EUC_KR)).length));
                break;

            case LghvProtocol.MMS_DELIVERY_TYPE:
                byteBuf.writeBytes(ConvertorUtil.convertPropertyToBytes(dataCnt, LghvProtocol.DATACNT_LENGTH));

                for (LghvDeliveryMultipartData mediaData : multipartDatas) {
                    if (mediaData == null) break;

                    // write <MMSInnerData> data to byteBuf
                    mediaData.toByteBuf(byteBuf);
                }

                break;


            default:
                throw new IllegalArgumentException("??? who are you");
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

        // Body
        this.encode = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.ENCODE_LENGTH);
        this.deliveryType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DELIVERY_TYPE_LENGTH);
        this.msgId = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.MSG_ID_LENGTH);
        this.gubun = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.GUBUN_LENGTH);
        this.contentsType = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.CONTENTS_TYPE_LENGTH);
        this.destAddr = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DESTADDR_LENGTH);
        this.callback = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.CALLBACK_LENGTH);
        this.reseller = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.RESELLER_LENGTH);
        this.subject = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DELIVERY_SUBJECT_LENGTH);

        switch (deliveryType) {
            case LghvProtocol.LMS_DELIVERY_TYPE:
                this.text = ConvertorUtil.getStrPropertyInByteBuf(byteBuf, super.msgLen - LghvProtocol.LMS_UNCHANGE_BODY_LEN);
                break;

            case LghvProtocol.MMS_DELIVERY_TYPE:
                this.dataCnt = Integer.parseInt(ConvertorUtil.getStrPropertyInByteBuf(byteBuf, LghvProtocol.DATACNT_LENGTH));

                for (int i=0; i<dataCnt; i++) {
                    LghvDeliveryMultipartData multipartData = new LghvDeliveryMultipartData();
                    multipartData.fromByteBuf(byteBuf);

                    multipartDatas[i] = multipartData;
                }
                break;

            default:
                throw new IllegalArgumentException("who are you ??? ");

        }
    }


    public static LghvDeliveryMessage createDeliveryMessage(String encodeType, String umsMsgId, String receiver, String callback, String reseller, String subject) {
        return LghvDeliveryMessage.builder()
                // Common Body
                .encode(encodeType)
                .msgId(umsMsgId)
                .gubun(LghvProtocol.DOMESTIC_GUBUN)
                .destAddr(receiver)
                .callback(callback)
                .reseller(reseller)
                .subject(subject)
                .build();
    }

    public void setMendatoryValues(boolean hasText, List<String> imageIds) {
        boolean hasImages = !imageIds.isEmpty();

        setMsgLen(hasImages);                          // set msgLen in header
        setContentType(hasText, hasImages);            // set contentType("TXT", "IMT", "IMG")
        setDeliveryType(hasImages);                    // set DeliveryType ("LMS", "MMS");
    }

    public void setMsgLen(boolean hasImages) {
        int msgLen = hasImages ? LghvProtocol.MMS_UNCHANGE_BODY_LEN : LghvProtocol.LMS_UNCHANGE_BODY_LEN;
        super.setMsgLen(msgLen);
    }


    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    private void setDeliveryType(boolean hasImages) {
        this.deliveryType = hasImages ? LghvProtocol.MMS_DELIVERY_TYPE : LghvProtocol.LMS_DELIVERY_TYPE;
    }



    public void addMediapartData(LghvDeliveryMultipartData multipartData) {
        this.dataCnt += 1;
        multipartDatas[this.dataCnt - 1] = multipartData; // dataCnt - 1 = index
    }


    public void setContentType(String contentType) {
        this.contentsType = contentType;
    }


    public void setContentType(boolean hasText, boolean hasImages) {
        if (hasText && hasImages) {
            this.contentsType = "IMT";
        }

        if (hasText && !hasImages) {
            this.contentsType = "TXT";
        }

        if (!hasText && hasImages) {
            this.contentsType = "IMG";
        }

//        throw new IllegalStateException("INVALID TYPE");
    }

    @Override
    public String toString() {
        return "LghvDeliveryMessage{" +
                "msgType='" + msgType + '\'' +
                ", msgLen=" + msgLen +
                ", tail='" + tail + '\'' + ", tail.length=" + tail.length() +
                ", encode='" + encode + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", msgId='" + msgId + '\'' +
                ", gubun='" + gubun + '\'' +
                ", contentsType='" + contentsType + '\'' +
                ", destAddr='" + destAddr + '\'' +
                ", callback='" + callback + '\'' +
                ", reseller='" + reseller + '\'' +
                ", subject='" + subject + '\'' +
                ", adFlag='" + adFlag + '\'' +
                ", reserved='" + reserved + '\'' +
                ", text='" + text + '\'' +
                ", ksubject='" + ksubject + '\'' +
                ", dataCnt=" + dataCnt +
                ", multipartDatas=" + Arrays.toString(multipartDatas) +
                '}';
    }

    public void setText(String text) {
        int textLENGTH = text.getBytes(Charset.forName(EUC_KR)).length;
        this.text = text;

        addMsgLen(textLENGTH);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LghvDeliveryMessage that = (LghvDeliveryMessage) object;
        return dataCnt == that.dataCnt && Objects.equals(encode, that.encode) && Objects.equals(deliveryType, that.deliveryType) && Objects.equals(msgId, that.msgId) && Objects.equals(gubun, that.gubun) && Objects.equals(contentsType, that.contentsType) && Objects.equals(destAddr, that.destAddr) && Objects.equals(callback, that.callback) && Objects.equals(reseller, that.reseller) && Objects.equals(subject, that.subject) && Objects.equals(adFlag, that.adFlag) && Objects.equals(reserved, that.reserved) && Objects.equals(text, that.text) && Objects.equals(ksubject, that.ksubject) && Arrays.equals(multipartDatas, that.multipartDatas);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(encode, deliveryType, msgId, gubun, contentsType, destAddr, callback, reseller, subject, adFlag, reserved, text, ksubject, dataCnt);
        result = 31 * result + Arrays.hashCode(multipartDatas);
        return result;
    }
}
