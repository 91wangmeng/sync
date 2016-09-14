package com.drore.cloud.entity;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class ComparisonData implements Data {

    private static final long serialVersionUID = 431218747760161993L;
    private String from_appid;
    private String from_appsecret;
    private String to_appid;
    private String to_appsecret;

    public String getFrom_appid() {
        return from_appid;
    }

    public void setFrom_appid(String from_appid) {
        this.from_appid = from_appid;
    }

    public String getFrom_appsecret() {
        return from_appsecret;
    }

    public void setFrom_appsecret(String from_appsecret) {
        this.from_appsecret = from_appsecret;
    }

    public String getTo_appid() {
        return to_appid;
    }

    public void setTo_appid(String to_appid) {
        this.to_appid = to_appid;
    }

    public String getTo_appsecret() {
        return to_appsecret;
    }

    public void setTo_appsecret(String to_appsecret) {
        this.to_appsecret = to_appsecret;
    }
}
