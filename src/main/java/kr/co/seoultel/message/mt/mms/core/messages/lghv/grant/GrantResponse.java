package kr.co.seoultel.message.mt.mms.core.messages.lghv.grant;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GrantResponse {
    private String ip;
    private String port;
    private String sid;
    private String status;
}