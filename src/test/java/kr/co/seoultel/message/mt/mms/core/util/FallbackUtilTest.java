package kr.co.seoultel.message.mt.mms.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.co.seoultel.message.core.dto.MessageDelivery;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FallbackUtilTest {

    private static final Gson gson = new GsonBuilder().create();
    private static final String fallbackJsonFilePath = "/Users/simjeonghun/github/seoultel/libraries/kr.co.seoultel.message.mt.mms-core/src/test/resources/fallbackMessageDelivery.json";

    @Test
    public void FallbackMessageDeliveryTest() {
        try {
            String fallbackMessageDeliveryString = FileUtil.readJsonFileAsString(fallbackJsonFilePath);
            MessageDelivery fallbackMessageDelivery = gson.fromJson(fallbackMessageDeliveryString, MessageDelivery.class);
            System.out.printf("fallbackMessageDelivery : %s", fallbackMessageDelivery);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void FallbackMessageDeliveryAssignDefaultSubjectTest() {
        try {
            String fallbackMessageDeliveryString = FileUtil.readJsonFileAsString(fallbackJsonFilePath);
            MessageDelivery fallbackMessageDelivery = gson.fromJson(fallbackMessageDeliveryString, MessageDelivery.class);
            System.out.printf("1 : %s\n", fallbackMessageDelivery);

            FallbackUtil.setFallbackSubject(fallbackMessageDelivery, "sd;lfsldkflsd");
            System.out.printf("2 : %s\n", fallbackMessageDelivery);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
