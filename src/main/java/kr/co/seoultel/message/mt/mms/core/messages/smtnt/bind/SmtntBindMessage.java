package kr.co.seoultel.message.mt.mms.core.messages.smtnt.bind;

import com.google.gson.annotations.SerializedName;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntMessage;
import kr.co.seoultel.message.mt.mms.core.messages.smtnt.SmtntProtocol;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
@Getter @Setter
public class SmtntBindMessage extends SmtntMessage {

    @SerializedName("BindId")
    private String bindId;

    @SerializedName("BindPwd")
    private String bindPwd;

    @SerializedName("Version")
    private final int version = 1;


    public SmtntBindMessage() {
        super(SmtntProtocol.SMTNT_BIND_METHOD_HEADER);
    }


    public SmtntBindMessage(String bindId, String bindPwd) {
        super(SmtntProtocol.SMTNT_BIND_METHOD_HEADER);

        this.bindId = bindId;
        this.bindPwd = bindPwd;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SmtntBindMessage that = (SmtntBindMessage) object;
        return Objects.equals(bindId, that.bindId) && Objects.equals(bindPwd, that.bindPwd) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bindId, bindPwd, version);
    }

    @Override
    public String toString() {
        return "SmtntBindMessage{" +
                "method='" + getMethod() + '\'' +
                ", bindId='" + bindId + '\'' +
                ", bindPwd='" + bindPwd + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
