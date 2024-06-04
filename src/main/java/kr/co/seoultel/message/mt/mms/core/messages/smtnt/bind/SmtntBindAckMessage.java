package kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
@Getter @Setter
public class SmtntBindAckMessage extends SmtntMessage {

    private transient final boolean hasBody = true;

    @SerializedName("Result")
    private int result;

    @SerializedName("BindId")
    private String bindId;

    @SerializedName("Speed")
    private int speed;

    @SerializedName("BeginTime")
    private int beginTime;

    @SerializedName("EndTime")
    private int endTime;

    @SerializedName("EffectiveTime")
    private int effectiveTime;


    public SmtntBindAckMessage() {
        super(SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER);
    }


    @Builder
    public SmtntBindAckMessage(int result, String bindId, int speed, int beginTime, int endTime, int effectiveTime) {
        super(SmtntProtocol.SMTNT_BIND_ACK_METHOD_HEADER);

        this.result = result;
        this.bindId = bindId;
        this.speed = speed;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.effectiveTime = effectiveTime;
    }

    @Override
    public String toString() {
        return "SmtntBindAckMessage{" +
                "method='" + method + '\'' +
                ", result='" + result + '\'' +
                ", bindId='" + bindId + '\'' +
                ", speed='" + speed + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SmtntBindAckMessage that = (SmtntBindAckMessage) object;
        return Objects.equals(result, that.result) && Objects.equals(bindId, that.bindId) && Objects.equals(speed, that.speed) && Objects.equals(beginTime, that.beginTime) && Objects.equals(endTime, that.endTime) && Objects.equals(effectiveTime, that.effectiveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, bindId, speed, beginTime, endTime, effectiveTime);
    }

}

