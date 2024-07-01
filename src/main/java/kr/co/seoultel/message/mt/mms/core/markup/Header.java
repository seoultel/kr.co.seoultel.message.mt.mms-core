package kr.co.seoultel.message.mt.mms.core.markup;

import lombok.ToString;

@ToString
class Header {

    protected final String meta = "<meta name=\"mms_skt_version\" content=\"4.0\"/>";
    protected String layout = "<layout>\r\n<root-layout width=\"220\" height=\"377\" background-color=\"#FFFFFF\"/>\r\n</layout>";

    public void addRegion() {
        int addFrom = layout.lastIndexOf("/layout");
    }
}
