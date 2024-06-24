package kr.co.seoultel.message.mt.mms.core.entity;

import lombok.Getter;

@Getter
public class ServerInfo {
    protected String ip;
    protected String port;

    protected ServerType sType;

    public ServerInfo(String ip, String port, ServerType sType) {
        this.ip = ip;
        this.port = port;
        this.sType = sType;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", sType=" + sType +
                '}';
    }
}
