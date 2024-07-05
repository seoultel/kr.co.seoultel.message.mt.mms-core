package kr.co.seoultel.message.mt.mms.core.util;

public class CommonUtilTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String str = CommonUtil.getStringConsistOfRandomNumber(10);
            System.out.println(str);
        }
    }
}
