package kr.co.seoultel.message.mt.mms.core.encrpyt;

import lombok.Getter;

@Getter
public enum EncryptType {
    LGHV_ENCRYPT_TYPE("AES/CBC/PKCS5Padding"),
    HIST_ENCRYPT_TYPE("AES/CBC/PKCS5Padding");

    private String str;

    EncryptType(String str) {
        this.str = str;
    }
}