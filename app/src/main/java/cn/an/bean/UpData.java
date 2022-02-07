package cn.an.bean;

import cn.bmob.v3.BmobObject;

public class UpData extends BmobObject {

    private String version;
    private String url;

    public String getVersion() {
        return version;
    }

    public String getUrl() {
        return url;
    }

    public String getNotice() {
        return notice;
    }

    private String notice;
}
