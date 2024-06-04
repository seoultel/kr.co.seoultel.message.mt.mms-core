package kr.co.seoultel.message.mt.mms.core.messages.lghv.grant;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class GrantRequest {
    private String id;
    private String pw;
}