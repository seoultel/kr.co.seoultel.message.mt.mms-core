package kr.co.seoultel.message.mt.mms.core.messages.smtnt.encrypt;

import lombok.Getter;

@Getter
public enum EncryptType {
    AES128("AES/CBC/PKCS5Padding");

    private String str;

    EncryptType(String str) {
        this.str = str;
    }
}