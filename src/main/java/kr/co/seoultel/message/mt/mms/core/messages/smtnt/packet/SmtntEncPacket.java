package kr.co.seoultel.message.mt.mms.core.messages.smtnt.packet;

import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Smtnt packet.
 */
@Slf4j
@Getter @Setter
@NoArgsConstructor
public class SmtntEncPacket extends SmtntPacket {

    public SmtntEncPacket(SmtntMessage smtntMessage) throws CryptoException {
        super(smtntMessage);
    }

    @Override
    public SmtntEncPacket fromJson(String json) throws Exception {
        return null;
    }

    //
//    public SmtntEncPacket(SmtntMessage smtntMessage) {
//        // Header;
//        this.header = new Header(smtntMessage.getMethod());
//
//        if (!smtntMessage.hasBody()) {
//            this.body = null;
//        } else {
//            String json = gson.toJson(smtntMessage);
//            this.body = EncryptionUtil.encryptTextByAes128AndBase64(json).get();
//        }
//    }
//
//    public SmtntMessage getSmtntMessage() {
//        /* Decrypt encryptedBody : json */
//        String decryptedBody = EncryptionUtil.decryptTextByBase64AndAes128((String) body).get();
//
//        SmtntMessage smtntMessage = null;
//        switch (this.header.getMethod()) {
//            case SMTNT_BIND_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntBindMessage.class);
//                break;
//
//            case SMTNT_BIND_ACK_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntBindAckMessage.class);
//                break;
//
//            case SMTNT_DELIVERY_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntDeliveryMessage.class);
//                break;
//
//            case SMTNT_DELIVERY_ACK_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntDeliveryAckMessage.class);
//                break;
//
//            case SMTNT_REPORT_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntReportMessage.class);
//                break;
//
//            case SMTNT_REPORT_ACK_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntReportAckMessage.class);
//                break;
//
//            case SMTNT_LINK_METHOD_HEADER:
//                smtntMessage = CommonConvertor.convertJsonToObject(decryptedBody, SmtntLinkMessage.class);
//                break;
//        }
//
//        return smtntMessage;
//    }
}
