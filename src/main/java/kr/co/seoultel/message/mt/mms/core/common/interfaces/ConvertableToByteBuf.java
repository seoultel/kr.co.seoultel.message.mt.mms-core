package kr.co.seoultel.message.mt.mms.core.common.interfaces;


import io.netty.buffer.ByteBuf;

public interface ConvertableToByteBuf {

    void toByteBuf(ByteBuf byteBuf);
    void fromByteBuf(ByteBuf byteBuf) throws Exception;
}

