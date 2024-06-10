package kr.co.seoultel.message.mt.mms.core.validate;

import kr.co.seoultel.message.mt.mms.core.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ValidateTest {

    public static void main(String[] args) {
        String localDateTimeString = "2024-06-07 13:35:26.000";
        String mcmpLocalDateFormat = DateUtil.parseFullLocalDateToMcmpLocalDateFormat(localDateTimeString);

        System.out.printf("mcmpLocalDateFormat : %s", mcmpLocalDateFormat);
    }

}
