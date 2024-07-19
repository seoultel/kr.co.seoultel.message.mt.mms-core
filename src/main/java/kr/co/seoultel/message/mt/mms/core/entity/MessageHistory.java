package kr.co.seoultel.message.mt.mms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import static kr.co.seoultel.message.mt.mms.core.common.constant.Constants.MMS_EXPIRE_TIME;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public class MessageHistory {

    private String messageId;
    private LocalDateTime submitTime;

    // public static int MSG_EXPIRED_MINUTES = 1;
    public static long EXPIRED_TIME = MMS_EXPIRE_TIME;

    public MessageHistory(String messageId) {
        this.messageId = messageId;
        this.submitTime = LocalDateTime.now();
    }


    public boolean isExpire() {
        Duration duration = Duration.between(submitTime, LocalDateTime.now());
        return duration.toSeconds() >= 15;
    }

//    public boolean isExpire() {
//        Duration duration = Duration.between(submitTime, LocalDateTime.now());
//        return duration.toMinutes() >= EXPIRED_TIME;
//    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MessageHistory that = (MessageHistory) object;
        return Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }
}
