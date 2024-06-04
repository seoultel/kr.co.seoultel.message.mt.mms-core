package kr.co.seoultel.message.mt.mms.core.messages.lghv.delivery;

import lombok.Getter;


public enum LghvDeliveryMultipartType {
    TEXT("t"), IMAGE("i"), KKO_BTN("z"), ETC("e");

    @Getter
    private final String dataType;
    LghvDeliveryMultipartType(String dataType) {
        this.dataType = dataType;
    }

    public static LghvDeliveryMultipartType getDataType(String dataType) {
        switch (dataType) {
            case "t":
                return TEXT;
            case "i":
                return IMAGE;
            case "z":
                return KKO_BTN;
            default:
                return ETC;
        }
    }
}