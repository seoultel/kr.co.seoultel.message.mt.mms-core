package kr.co.seoultel.message.mt.mms.core.messages.hist.factory;

import io.netty.buffer.ByteBufUtil;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;
import kr.co.seoultel.message.mt.mms.core.encrpyt.HistEncryptor;
import kr.co.seoultel.message.mt.mms.core.common.protocol.HistProtocol;
import kr.co.seoultel.message.mt.mms.core.messages.hist.bind.HistBindMessage;
import kr.co.seoultel.message.mt.mms.core.messages.hist.cert.HistCertMessage;

public class HistMsgFactory {

    private final HistEncryptor histEncryptor;
    private final int keypos;
    private final String pwd;

    public HistMsgFactory(int keypos, String pwd) throws CryptoException {
        this.keypos = keypos;
        this.pwd = pwd;

        this.histEncryptor = new HistEncryptor(keypos, pwd);
    }

    public HistCertMessage getHistCertMessage(String id, String pwd, int keypos) throws CryptoException {
        String encryptedBySha256 = ByteBufUtil.hexDump(histEncryptor.encryptBySha256(pwd));
        String encryptedPwd = histEncryptor.encryptTextByAes256AndBase64(encryptedBySha256).orElseThrow(() -> new CryptoException(""));
        return new HistCertMessage(id, encryptedPwd, String.valueOf(keypos));
    }

    public HistBindMessage getHistBindMessage(String id, String pwd, String certKey, String serverType) throws CryptoException {
        String encryptedBySha256 = ByteBufUtil.hexDump(histEncryptor.encryptBySha256(pwd));
        String encryptedPwd = histEncryptor.encryptTextByAes256AndBase64(encryptedBySha256).orElseThrow(() -> new CryptoException(""));

        return HistBindMessage.builder()
                .id(id)
                .pwd(encryptedPwd)
                .certKey(certKey)
                .type(HistProtocol.DEFAULT_TYPE)
                .kind(serverType)
                .keypos(keypos)
                .build();

    }

}
