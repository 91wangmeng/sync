package com.drore.cloud.entity;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class SyncData implements Data {
    private static final long serialVersionUID = 276216974922382604L;
    private String from_resource;
    private String to_resource;

    public String getFrom_resource() {
        return from_resource;
    }

    public void setFrom_resource(String from_resource) {
        this.from_resource = from_resource;
    }

    public String getTo_resource() {
        return to_resource;
    }

    public void setTo_resource(String to_resource) {
        this.to_resource = to_resource;
    }
}
