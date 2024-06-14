package kr.co.seoultel.message.mt.mms.core.common.interfaces;


import io.netty.buffer.ByteBuf;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.CryptoException;

public interface ConvertableToByteBuf {

    void toByteBuf(ByteBuf byteBuf) throws CryptoException;
    void fromByteBuf(ByteBuf byteBuf) throws Exception;
}

