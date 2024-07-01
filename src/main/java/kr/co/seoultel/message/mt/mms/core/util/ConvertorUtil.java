package kr.co.seoultel.message.mt.mms.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.FormatException;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.message.MessageDeserializationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ConvertorUtil {
    protected final static Gson gson  = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();


    public static SOAPMessage convertStringToSOAPMessage(String soapMessageStr) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        return factory.createMessage(null, new ByteArrayInputStream(soapMessageStr.getBytes()));
    }

    public static String convertStringToXml(String str) {
        String pattern = "<\\?xml[\\s\\S]*?<\\/env:Envelope>";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(str);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return null; // or throw an exception if XML not found
        }
    }


    public static <T> T convertBytesToObject(byte[] body, Class<T> cls) throws FormatException {
        try {
            return gson.fromJson(new String(body), cls);
        } catch (Exception var3) {
            throw new MessageDeserializationException("[DESERIALIZED EXCEPTION] FAILED TO byte[] to MessageDelivery.class");
        }
    }

    public static String convertObjectToJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T convertJsonToObject(String json, Class<T> cls) {
        try {
            return gson.fromJson(json, cls);
        } catch (JsonSyntaxException var3) {
            return null;
        }
    }

    public static byte[] convertPropertyToBytesByEucKr(String property, int maxLength) {
        if (Objects.isNull(property)) {
            property = "";
        }

        byte[] temp = new byte[maxLength];
        Arrays.fill(temp, (byte)0);
        byte[] propertyBytes = property.getBytes(Charset.forName("euc-kr"));
        System.arraycopy(propertyBytes, 0, temp, 0, propertyBytes.length > maxLength ? maxLength : propertyBytes.length);
        return temp;
    }

    public static byte[] convertPropertyToBytesByEucKr(Integer property, int maxLength) {
        String propertyStr = property == null ? "" : String.valueOf(property);
        byte[] temp = new byte[maxLength];
        Arrays.fill(temp, (byte) 0);
        byte[] propertyBytes = propertyStr.getBytes(Charset.forName("euc-kr"));
        System.arraycopy(propertyBytes, 0, temp, 0, propertyBytes.length > maxLength ? maxLength : propertyBytes.length);
        return temp;
    }

    public static byte[] convertPropertyToBytes(String property, int maxLength) {
        if (Objects.isNull(property)) {
            property = "";
        }

        byte[] temp = new byte[maxLength];
        Arrays.fill(temp, (byte)0);
        byte[] propertyBytes = property.getBytes(Charset.forName("euc-kr"));
        System.arraycopy(propertyBytes, 0, temp, 0, propertyBytes.length > maxLength ? maxLength : propertyBytes.length);
        return temp;
    }

    public static byte[] convertPropertyToBytes(int property, int maxLength) {
        String propertyStr = Objects.isNull(property) ? "" : String.valueOf(property);
        byte[] temp = new byte[maxLength];
        Arrays.fill(temp, (byte)0);
        byte[] propertyBytes = propertyStr.getBytes(Charset.forName("euc-kr"));
        System.arraycopy(propertyBytes, 0, temp, 0, propertyBytes.length > maxLength ? maxLength : propertyBytes.length);
        return temp;
    }

    public static String getStrPropertyInByteBuf(ByteBuf byteBuf, int length) {
        return byteBuf.readSlice(length).toString(Charset.forName("euc-kr")).replace("\u0000", "");
    }

    public static String getStrPropertyInByteBuf(ByteBuf byteBuf, int length, int skipBytesSizeBeforeToRead) {
        byteBuf.skipBytes(skipBytesSizeBeforeToRead);
        return byteBuf.readSlice(length).toString(Charset.forName("euc-kr")).replace("\u0000", "");
    }

    public static String getStrPropertyInByteBuf(ByteBuf byteBuf, int length, int skipBytesSizeBeforeToRead, int skipByteSizeAfterToRead) {
        byteBuf.skipBytes(skipBytesSizeBeforeToRead);
        String str = byteBuf.readSlice(length).toString(Charset.forName("euc-kr")).replace("\u0000", "");
        byteBuf.skipBytes(skipByteSizeAfterToRead);
        return str;
    }

    public static int getIntPropertyInByteBuf(ByteBuf byteBuf, int length) {
        return Integer.parseInt(getStrPropertyInByteBuf(byteBuf, length));
    }

    public static int getIntPropertyInByteBuf(ByteBuf byteBuf, int length, int skipBytesSizeBeforeToRead) {
        return Integer.parseInt(getStrPropertyInByteBuf(byteBuf, length, skipBytesSizeBeforeToRead));
    }

    public static int getIntPropertyInByteBuf(ByteBuf byteBuf, int length, int skipBytesSizeBeforeToRead, int skipByteSizeAfterToRead) {
        int result = Integer.parseInt(getStrPropertyInByteBuf(byteBuf, length, skipBytesSizeBeforeToRead));
        byteBuf.skipBytes(skipByteSizeAfterToRead);
        return result;
    }

    public static String getHexDump(byte[] bytes) {
        return ByteBufUtil.hexDump(bytes);
    }
}
