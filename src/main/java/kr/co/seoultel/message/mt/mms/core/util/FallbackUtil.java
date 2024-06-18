package kr.co.seoultel.message.mt.mms.core.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Objects;

import kr.co.seoultel.message.core.dto.MessageDelivery;
import kr.co.seoultel.message.core.dto.Result;
import kr.co.seoultel.message.core.dto.fallback.Fallback;
import kr.co.seoultel.message.core.dto.mms.Submit;
import kr.co.seoultel.message.core.module.DeliveryParser;
import kr.co.seoultel.message.mt.mms.core.entity.DeliveryType;

import static kr.co.seoultel.message.mt.mms.core.entity.DeliveryType.*;

/**
 * The type Fallback util.
 */
public class FallbackUtil {

    /**
     * Gets fallback.
     *
     * @param messageDelivery the message delivery
     * @return the fallback
     */
    public static Fallback getFallback(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery);
    }

    /**
     * Is fallback boolean.
     *
     * @param messageDelivery the message delivery
     * @return the boolean
     */
    public static boolean isFallback(MessageDelivery messageDelivery) {
        return messageDelivery.getContent().containsKey(Fallback.FALLBACK_FLAG);
    }

    /**
     * Gets fallback dst msg id.
     *
     * @param messageDelivery the message delivery
     * @return the fallback dst msg id
     */
    public static String getFallbackDstMsgId(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getDstMsgId();
    }

    /**
     * Gets fallback subject.
     *
     * @param messageDelivery the message delivery
     * @return the fallback subject
     */
    public static String getFallbackSubject(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getSubject();
    }

    /**
     * Gets fallback channel.
     *
     * @param messageDelivery the message delivery
     * @return the fallback channel
     */
    public static String getFallbackChannel(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getChannel();
    }

    /**
     * Gets fallback message.
     *
     * @param messageDelivery the message delivery
     * @return the fallback message
     */
    public static String getFallbackMessage(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getContent();
    }

    /**
     * Gets fallback service provider.
     *
     * @param messageDelivery the message delivery
     * @return the fallback service provider
     */
    public static String getFallbackServiceProvider(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getServiceProvider();
    }

    /**
     * Gets fallback file ids.
     *
     * @param messageDelivery the message delivery
     * @return the fallback file ids
     */
    public static List<String> getFallbackFileIds(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getFileIds();
    }

    /**
     * Gets fallback origin code.
     *
     * @param messageDelivery the message delivery
     * @return the fallback origin code
     */
    public static String getFallbackOriginCode(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getOriginCode();
    }

    /**
     * Gets fallback result.
     *
     * @param messageDelivery the message delivery
     * @return the fallback result
     */
    public static List<String> getFallbackTriedMnos(MessageDelivery messageDelivery) {
        return (List<String>) Objects.requireNonNull(DeliveryParser.getFallback(messageDelivery).getResult()).getOrDefault(Result.TRIED_MNOS, new ArrayList<String>());
    }

    /**
     * Gets fallback result.
     *
     * @param messageDelivery the message delivery
     * @return the fallback result
     */
    public static Map<String, Object> getFallbackResult(MessageDelivery messageDelivery) {
        return DeliveryParser.getFallback(messageDelivery).getResult();
    }


    /**
     * if is fallback message, get fallback channel
     * else default channel
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the channel(SMS/MMS) in messageDelivery
     */
    public static String getChannel(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackChannel(messageDelivery) : messageDelivery.getChannel();
    }


    /**
     * if is fallback message, get fallback DstMsgId
     * else default DstMsgId
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the DstMsgId(Customer PK) in messageDelivery
     */
    public static String getDstMsgId(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackDstMsgId(messageDelivery) : messageDelivery.getDstMsgId();
    }

    /**
     * if is fallback message, get fallback dstMsgId
     * else default dstMsgId
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the dstMsgId in messageDelivery
     */
    public static String getSubject(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackSubject(messageDelivery) : (String) messageDelivery.getContent().getOrDefault(Submit.SUBJECT, "���� ����");
    }

    /**
     * if is fallback message, get fallback message
     * else default message
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the message in messageDelivery
     */
    public static String getMessage(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackMessage(messageDelivery) : (String) Objects.requireNonNullElse(messageDelivery.getContent().get(Submit.MESSAGE), "");
    }

    /**
     * if is fallback message, get fallback serviceProvider
     * else default serviceProvider
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the serviceProvider in messageDelivery
     */
    public static String getServiceProvider(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackServiceProvider(messageDelivery) : messageDelivery.getServiceProvider();
    }

    /**
     * if is fallback message, get fallback mediaFiles(fileIds)
     * else default mediaFiles(fileIds)
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the mediaFiles(fileIds) in messageDelivery
     */
    public static List<String> getMediaFiles(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackFileIds(messageDelivery) : (List<String>) messageDelivery.getContent().getOrDefault(Submit.MEDIA_FILES, List.of());
    }

    /**
     * if is fallback message, get fallback originCode
     * else default originCode
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the originCode in messageDelivery
     */
    public static String getOriginCode(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackOriginCode(messageDelivery) : (String) messageDelivery.getContent().get(Submit.ORIGIN_CODE);
    }

    /**
     * if is fallback message, get fallback result
     * else default result
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the result in messageDelivery
     */
    public static List<String> getTriedMnos(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackTriedMnos(messageDelivery)
                                        : (List<String>) messageDelivery.getResult().getOrDefault(Result.TRIED_MNOS, new ArrayList<String>());
    }

    /**
     * if is fallback message, get fallback result
     * else default result
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @return the result in messageDelivery
     */
    public static Map<String, Object> getResult(MessageDelivery messageDelivery) {
        return isFallback(messageDelivery) ? getFallbackResult(messageDelivery) :  messageDelivery.getResult();
    }


    /**
     * Sets fallback channel.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param channel         the channel, assign to fallback's channel
     */
    public static void setFallbackChannel(MessageDelivery messageDelivery, String channel) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setChannel(channel);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback dst msg id.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param dstMsgId        the dstMsgId, assign to fallback's dstMsgId
     */
    public static void setFallbackDstMsgId(MessageDelivery messageDelivery, String dstMsgId) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setDstMsgId(dstMsgId);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback subject.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param subject         the subject, assign to fallback's subject
     */
    public static void setFallbackSubject(MessageDelivery messageDelivery, String subject) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setSubject(subject);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback message.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param message         the subject, assign to fallback's subject
     */
    public static void setFallbackMessage(MessageDelivery messageDelivery, String message) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setContent(message);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback origin code.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param originCode      the originCode, assign to fallback's originCode
     */
    public static void setFallbackOriginCode(MessageDelivery messageDelivery, String originCode) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setOriginCode(originCode);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback service provider.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param serviceProvider the serviceProvider, assign to fallback's serviceProvider
     */
    public static void setFallbackServiceProvider(MessageDelivery messageDelivery, String serviceProvider) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setServiceProvider(serviceProvider);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }


    /**
     * Sets fallback result.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param triedMnos          the result, assign to fallback's triedMnos
     */
    public static void setFallbackTriedMnos(MessageDelivery messageDelivery, List<String> triedMnos) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.getResult().put(Result.TRIED_MNOS, triedMnos);

        messageDelivery.getContent().replace(Fallback.FALLBACK, fallback);
    }

    /**
     * Sets fallback result.
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param result          the result, assign to fallback's result
     */
    public static void setFallbackResult(MessageDelivery messageDelivery, Map<String, Object> result) {
        Fallback fallback = getFallback(messageDelivery);
        fallback.setResult(result);

        messageDelivery.getContent().put(Fallback.FALLBACK, fallback);
    }


    /**
     * if messageDelivery is fallback, Sets fallback channel
     * else set default channel
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param channel         the channel
     */
    public static void setChannel(MessageDelivery messageDelivery, String channel) {
        if (isFallback(messageDelivery)) setFallbackChannel(messageDelivery, channel);
        else messageDelivery.setChannel(channel);
    }

    /**
     * if messageDelivery is fallback, Sets fallback dstMsgId
     * else set default dstMsgId
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param dstMsgId        the dstMsgId
     */
    public static void setDstMsgId(MessageDelivery messageDelivery, String dstMsgId) {
        if (isFallback(messageDelivery)) setFallbackDstMsgId(messageDelivery, dstMsgId);
        else messageDelivery.setDstMsgId(dstMsgId);
    }

    /**
     * if messageDelivery is fallback, Sets fallback subject
     * else set default subject
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param subject         the subject
     */
    public static void setSubject(MessageDelivery messageDelivery, String subject) {
        if (isFallback(messageDelivery)) setFallbackSubject(messageDelivery, subject);
        else messageDelivery.getContent().put(Submit.SUBJECT, subject);
    }

    /**
     * if messageDelivery is fallback, Sets fallback message
     * else set default message
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param message         the message
     */
    public static void setMessage(MessageDelivery messageDelivery, String message) {
        if (isFallback(messageDelivery)) setFallbackMessage(messageDelivery, message);
        else messageDelivery.getContent().put(Submit.MESSAGE, message);
    }


    /**
     * if messageDelivery is fallback, Sets fallback originCode
     * else set default originCode
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param originCode      the originCode
     */
    public static void setOriginCode(MessageDelivery messageDelivery, String originCode) {
        if (isFallback(messageDelivery)) setFallbackOriginCode(messageDelivery, originCode);
        else messageDelivery.getContent().put(Submit.ORIGIN_CODE, originCode);
    }

    /**
     * if messageDelivery is fallback, Sets fallback serviceProvider
     * else set default serviceProvider
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param serviceProvider the serviceProvider
     */
    public static void setServiceProvider(MessageDelivery messageDelivery, String serviceProvider) {
        if (isFallback(messageDelivery)) setFallbackServiceProvider(messageDelivery, serviceProvider);
        else messageDelivery.setServiceProvider(serviceProvider);
    }


    /**
     * if messageDelivery is fallback, Sets fallback result
     * else set default result
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param triedMno          the result
     */
    public static void setTriedMno(MessageDelivery messageDelivery, List<String> triedMno) {
        if (isFallback(messageDelivery)) setFallbackTriedMnos(messageDelivery, triedMno);
        else messageDelivery.getContent().replace(Result.TRIED_MNOS, triedMno);
    }

    /**
     * if messageDelivery is fallback, Sets fallback result
     * else set default result
     *
     * @param messageDelivery MessageDelivery be routed from MCMP Platform
     * @param result          the result
     */
    public static void setResult(MessageDelivery messageDelivery, Map<String, Object> result) {
        if (isFallback(messageDelivery)) setFallbackResult(messageDelivery, result);
        else messageDelivery.setResult(result);
    }
}
