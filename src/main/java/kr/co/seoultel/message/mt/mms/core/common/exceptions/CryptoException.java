package kr.co.seoultel.message.mt.mms.core.common.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptoException extends Exception {

    public CryptoException(String message) {
        super(message);
    }
}
