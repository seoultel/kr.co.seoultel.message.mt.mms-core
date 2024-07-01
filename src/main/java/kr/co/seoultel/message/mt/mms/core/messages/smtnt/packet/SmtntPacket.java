package kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.ConvertableToJson;
import kr.co.seoultel.message.mt.mms.core.common.interfaces.Zipper;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.common.protocol.SmtntProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.SmtntDeliveryMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.file.SmtntUploadFileAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.file.SmtntUploadFileMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.fileName.SmtntUploadFileNameAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.fileName.SmtntUploadFileNameMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish.SmtntFinishUploadAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.delivery.mms.upload.finish.SmtntFinishUploadMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.encrypt.SmtntEncryptor;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.link.SmtntLinkMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.report.SmtntReportMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind.SmtntBindMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.priv.SmtntPrivateKeyAckMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.priv.SmtntPrivateKeyMessage;
import kr.co.seoultel.message.mt.mms.core.util.ConvertorUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * The type Smtnt packet.
 */
@Slf4j
@Getter @Setter
@NoArgsConstructor
public class SmtntPacket implements ConvertableToByteBuf, ConvertableToJson {
    protected final transient Gson gson = new GsonBuilder().disableHtmlEscaping().create();


    @SerializedName("Header")
    protected Header header;

    @SerializedName("Body")
    protected Object body;


    /*
     * SMTNT SENDER -> SMTNT 사용
     * EX) BIND, DELIVERY, REPORT_ACK, LINK ...
     * // TODO : BIND & BIND_ACK 암호화 예외 처리
     */
    public SmtntPacket(SmtntMessage smtntMessage) throws CryptoException {
        // Header;
        this.header = new Header(smtntMessage.getMethod());

        // Body;
        if (!smtntMessage.hasBody()) {
            this.body = null;
        } else {
            switch (smtntMessage.getMethod()) {
                /*
                 * 비암호화 방식을 사용하는 경우 로그인 요청/응답(BIND)을 할 때만
                 * Body 데이터를 AES128 & Base64로 암호화/복호화한다.
                 */
                case SmtntProtocol.SMTNT_BIND_METHOD_HEADER:
                case SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER:
                    String json = gson.toJson(smtntMessage);
                    this.body = SmtntEncryptor.encryptTextByAes128AndBase64(json).orElseThrow(() -> new CryptoException(""));
                    // throw new SmtntCryptoException(getHeader().method, "SDFSDF");
                    break;

                default:
                    this.body = smtntMessage;
                    break;
            }
        }
    }

//    "Body":{
//        "FileExtension":".jpg"
//    }

    public void setMethod(String method) {
        this.header = new Header(method);
    }


    /*
     * **********************************
     * ** render Json from SmtntPacket **
     * **********************************
     *
     * EX)
     * {
     *   "Header" : {
     *       "Method":"BindReq"
     *   },
     *   "Body":"BB1ytDwZ5zpUkyA5p/WWJHRenpLPQSga5WmUHKrGXDjJuLGzo0Cl0TaDt1d2nQg70CYog5+M5JeGPbCzheg=="
     * }
     */
    @Override
    public String toJson() {
        return gson.toJson(this);
    }

    @Override
    public SmtntPacket fromJson(String json) throws Exception {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        JsonObject headerJsonObject = jsonObject.getAsJsonObject("Header");
        this.header = gson.fromJson(headerJsonObject.toString(), Header.class);

        JsonElement bodyJsonElement = jsonObject.get("Body");
        switch (header.getMethod()) {
            case SmtntProtocol.SMTNT_BIND_METHOD_HEADER:
                json = SmtntEncryptor.decryptTextByBase64AndAes128(bodyJsonElement.getAsString()).orElseThrow(() -> new CryptoException(String.format("Fail to decrypt in %s", SmtntProtocol.SMTNT_BIND_METHOD_HEADER)));
                this.body = gson.fromJson(json, SmtntBindMessage.class);
                break;

            case SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER:
                json = SmtntEncryptor.decryptTextByBase64AndAes128(bodyJsonElement.getAsString()).orElseThrow(() -> new CryptoException(String.format("Fail to decrypt in %s", SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER)));
                this.body = gson.fromJson(json, SmtntBindAckMessage.class);
                // throw new SmtntCryptoException(getHeader().method, "SDFSDF");
                break;

            case SmtntProtocol.SMTNT_LINK_METHOD_HEADER:
                this.body = new SmtntLinkMessage();
                break;

            case SmtntProtocol.SMTNT_LINK_ACK_METHOD_HEADER:
                this.body = new SmtntLinkAckMessage();
                break;

            default:
                Class cls = getMsgClassByMethod(header.getMethod());
                this.body = gson.fromJson(bodyJsonElement.toString(), cls);
                break;
        }

        return this;
    }


    @Override
    public void toByteBuf(ByteBuf byteBuf) {
        // 압축
        byte[] bytes = Zipper.toZip(toJson());

        // Content-Length, 압축한 byte[] bytes의 길이, 가져오기
        String contentLength = getContentLength(bytes.length);

        byteBuf.writeBytes(contentLength.getBytes(StandardCharsets.UTF_8));
        byteBuf.writeBytes(bytes);
    }


    /*
     * fyllPacket
     * EX) Content-Length:135[CR][LF]78DAAB56F2484D4C492D5...A42
     */
    @Override
    public void fromByteBuf(ByteBuf byteBuf) throws Exception {
        /*
         * ** Content-Length:??\r\n 에서 ??(ContentLength) 값을 가져온다 **
         * colonIndex : ?? 앞에 ":"의 인덱스
         * carriageReturnCharacterIndex : ?? 뒤에 있는 "\r"의 인덱스
         *
         * ** Content-Length **
         * -> ByteBuf 에서 colonIndex 인덱스와 carriageReturnCharacterIndex 인덱스 사이에 있는 값을 가져온다.
         * -> 해당 값은 SmtntPacket's json -> 암호화 -> byte[] -> toHexString 에서 toHexString의 길이를 의미하며,
         * -> 해당 길이만큼 ByteBuf 에서 데이터를 읽어 SmtntPakcet 클래스로 파싱할 것이다.
         */
        int colonIndex = byteBuf.bytesBefore((byte) ':') + 1;
        int carriageReturnCharacterIndex = byteBuf.bytesBefore((byte) '\r') ;
        int contentLength = ConvertorUtil.getIntPropertyInByteBuf(byteBuf, carriageReturnCharacterIndex - colonIndex, colonIndex, 2);

        /* HexString 데이터를 가져온다. */
        byte[] bytes = ByteBufUtil.getBytes(byteBuf.readSlice(contentLength));
        String json = Zipper.fromZip(bytes);

        /* 위에서 가져온 json으로부터 SmtntPacket 객체를 생성한다. */
        SmtntPacket smtntPacket = new SmtntPacket();
        smtntPacket.fromJson(json);

        /*
         * SmtntPacket 객체에는 Header와 Body 값이 존재한다.
         * 1. Header : Header 또한 Json 형태이며 "Method" 키 안에 해당 패킷이 어떠한 패킷인지에 대한 정보가 적혀있다.
         * 2. Body : Body는 SmtntMessage를 상속받은 클래스들을 Json 형태로 만든 후, AES128 + BASE64로 암호화한 문자열 값이 들어간다.
         */
        this.header = smtntPacket.getHeader();
        this.body = smtntPacket.getBody();
    }

    protected String getContentLength(int length) {
        return String.format("Content-Length:%d\r\n", length);
    }

    protected Class getMsgClassByMethod(String method) {
        switch (method) {
            case SmtntProtocol.SMTNT_PRIVATE_KEY_METHOD_HEADER:
                return SmtntPrivateKeyMessage.class;
            case SmtntProtocol.SMTNT_PRIVATE_KEY_ACK_METHOD_HEADER:
                return SmtntPrivateKeyAckMessage.class;

            case SmtntProtocol.SMTNT_BIND_METHOD_HEADER:
                return SmtntBindMessage.class;
            case SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER:
                return SmtntBindAckMessage.class;

            case SmtntProtocol.SMTNT_LINK_METHOD_HEADER:
                return SmtntLinkMessage.class;
            case SmtntProtocol.SMTNT_LINK_ACK_METHOD_HEADER:
                return SmtntLinkAckMessage.class;

            case SmtntProtocol.SMTNT_DELIVERY_METHOD_HEADER:
                return SmtntDeliveryMessage.class;
            case SmtntProtocol.SMTNT_DELIVERY_ACK_METHOD_HEADER:
                return SmtntDeliveryAckMessage.class;

            case SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_METHOD_HEADER:
                return SmtntUploadFileNameMessage.class;
            case SmtntProtocol.SMTNT_UPLOAD_FILE_NAME_ACK_METHOD_HEADER:
                return SmtntUploadFileNameAckMessage.class;
            case SmtntProtocol.SMTNT_UPLOAD_FILE_METHOD_HEADER:
                return SmtntUploadFileMessage.class;
            case SmtntProtocol.SMTNT_UPLOAD_FILE_ACK_METHOD_HEADER:
                return SmtntUploadFileAckMessage.class;
            case SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_METHOD_HEADER:
                return SmtntFinishUploadMessage.class;
            case SmtntProtocol.SMTNT_FINISH_UPLOAD_FILE_ACK_METHOD_HEADER:
                return SmtntFinishUploadAckMessage.class;

            case SmtntProtocol.SMTNT_REPORT_METHOD_HEADER:
                return SmtntReportMessage.class;
            case SmtntProtocol.SMTNT_REPORT_ACK_METHOD_HEADER:
                return SmtntReportAckMessage.class;

            default:
                throw new AssertionError();
        }
    }

    public static class Header {

        @SerializedName("Method")
        private final String method;

        public Header(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }

        @Override
        public String toString() {
            return "{" +
                        "method='" + method + '\'' +
                    "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Header header = (Header) o;
            return Objects.equals(method, header.method);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(method);
        }
    }


    @Override
    public String toString() {
        return "SmtntPacket{" +
                "header=" + header +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmtntPacket that = (SmtntPacket) o;
        return Objects.equals(header, that.header) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, body);
    }
}
