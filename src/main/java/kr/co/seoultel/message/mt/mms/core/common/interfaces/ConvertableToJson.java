package kr.co.seoultel.message.mt.mms.core.common.interfaces;

public interface ConvertableToJson {

    String toJson();
    Object fromJson(String json) throws Exception;
}
